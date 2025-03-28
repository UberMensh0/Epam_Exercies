package com.epam.rd.autocode.assessment.basics.service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

public class CsvStorageImpl implements CsvStorage {
    private String encoding;
    private String quoteCharacter;
    private String valuesDelimiter;
    private boolean headerLine;

    // Default constructor with default properties
    public CsvStorageImpl() {
        this.encoding = "UTF-8";
        this.quoteCharacter = "\"";
        this.valuesDelimiter = ",";
        this.headerLine = true;
    }

    // Constructor with custom properties
    public CsvStorageImpl(Map<String, String> props) {
        this.encoding = props.getOrDefault("encoding", "UTF-8");
        this.quoteCharacter = props.getOrDefault("quoteCharacter", "\"");
        this.valuesDelimiter = props.getOrDefault("valuesDelimiter", ",");
        this.headerLine = Boolean.parseBoolean(props.getOrDefault("headerLine", "true"));
    }

    @Override
    public <T> List<T> read(InputStream source, Function<String[], T> mapper) throws IOException {
        List<T> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(source, Charset.forName(encoding)));
        String line;
        boolean isFirstLine = headerLine;

        while ((line = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false; // Skip the first line if headerLine is true
                continue;
            }
            String[] values = parseCsvLine(line);
            result.add(mapper.apply(values));
        }

        reader.close();
        return result;
    }

    @Override
    public <T> void write(OutputStream dest, List<T> values, Function<T, String[]> mapper) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(dest, Charset.forName(encoding)));
        for (int i = 0; i < values.size(); i++) {
            T obj = values.get(i);
            String[] fields = mapper.apply(obj);
            writer.write(formatCsvLine(fields));
            if (i < values.size() - 1) {
                writer.newLine();
            }
        }
        writer.flush();
        writer.close();
    }

    private String[] parseCsvLine(String line) {
        List<String> result = new ArrayList<>();
        boolean insideQuotes = false;
        StringBuilder current = new StringBuilder();
        char[] chars = line.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (c == quoteCharacter.charAt(0)) {
                insideQuotes = !insideQuotes;
            } else if (c == valuesDelimiter.charAt(0) && !insideQuotes) {
                result.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }

        result.add(current.toString());
        return result.toArray(new String[0]);
    }

    private String formatCsvLine(String[] fields) {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            if (field == null) {
                field = "";
            } else if (field.contains(valuesDelimiter)) {
                field = quoteCharacter + field + quoteCharacter;
            }
            formatted.append(field);
            if (i < fields.length - 1) {
                formatted.append(valuesDelimiter);
            }
        }
        return formatted.toString();
    }
}
