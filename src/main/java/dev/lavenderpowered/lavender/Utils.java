package dev.lavenderpowered.lavender;

import net.minestom.server.ping.ResponseData;
import net.minestom.server.utils.identity.NamedAndIdentified;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static String readFromFile(String filename, String noFileResponse) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return noFileResponse; // Fallback
        }
    }

    public static void readCSL(ResponseData responseData, String noFileResponse) {
        try (BufferedReader br = new BufferedReader(new FileReader("customsl.txt"))) {
            List<String> lines = br.lines().toList();
            for (String line : lines) {
                responseData.addEntry(NamedAndIdentified.named(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
            responseData.addEntry(NamedAndIdentified.named(noFileResponse));
        }
    }
}
