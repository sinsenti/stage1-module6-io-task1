
//# Task 1
//
//
//        ## File uploading
//
//        Dear student, in this task you should upload data from `Profile.txt` file, parse and map on class `Profile` **using only `java.io` assets.
//Using other options for uploading are prohibited. For other purposes you can use only what you have already studied from previous stages and modules.**
//
//Example of `Profile.txt` in the folder `resources` with profile data:
//
//        ```
//Name: Anna
//Age: 25
//Email: anna@mailserver.com
//Phone: 1234567890
//        ```
//
//Also, you have class `FileReader` with method `getDataFromFile(File file)` which bring `File` object and should
//return `Profile` object with data from the file.
//As a small tip we recommend you follow the next logic:
//
//        ```
//        1. Reading file data into string
//2. Parse this string for key-value pairs
//3. Create Profile
//```
//
//        - Feel free for creating additional methods and classes, but keep in mind that `getDataFromFile` method will be invoked
//by tests in order check validity of your solution.
//- Uploading logic(input streams) should be only in `FileReader` class.
//        - Don't change folder hierarchy. Don't change method signature.
//        - Please, write your code according to java **code style, formation, best practices**. If you are not sure in formation, you can use default `Intelij IDEA` code formatting (`ctrl + alt + L`).
//
//Good luck :)
//
//
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
