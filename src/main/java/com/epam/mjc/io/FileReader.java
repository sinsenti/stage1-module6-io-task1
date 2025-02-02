package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return null;
        }
        String content = fileContent.toString();
        String[] lines = content.split("\n");
        String name = "";
        int age = 0;
        String email = "";
        long phone = 0;
        for (String line : lines) {
            if (line.contains(":")) {
                String[] keyValue = line.split(":", 2);
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();
                switch (key) {
                    case "Name":
                        name = value;
                        break;
                    case "Age":
                        age = Integer.parseInt(value);
                        break;
                    case "Email":
                        email = value;
                        break;
                    case "Phone":
                        phone = Long.parseLong(value);
                        break;
                    default:
                        break;
                }
            }
        }

        return new Profile(name, age, email, phone);
    }
}
