package dev.autism;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Util {

    public static void downloadFile(String downloadDir) {
        File dir = new File(downloadDir);
        if (!dir.exists()) {
            System.err.println("The mods directory does not exist. Aborting download.");
            return;
        }


        String[] parts = "https://breeze.rip/dashboard/breeze-forge.jar".split("/");
        String fileName = parts[parts.length - 1];
        String filePath = downloadDir + File.separator + fileName;

        try {
            URL website = new URL("https://breeze.rip/dashboard/breeze-forge.jar");
            try (InputStream in = website.openStream()) {
                Files.copy(in, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Downloaded: Yes");
                System.out.println("U can close it now");
            }
        } catch (IOException e) {
            System.err.println("Failed to download: " + e.getMessage());
        }
    }

        public static void install() throws IOException {
        String downloadDir;
        String forge;

        if (OSHelper.operatingSystem.contains("win")) {
            downloadDir = "C:\\Users\\" + OSHelper.whoami + "\\AppData\\Roaming\\.minecraft\\mod";
            forge = "C:\\Users\\" + OSHelper.whoami + "\\AppData\\Roaming\\.minecraft\\config\\forge.cfg";
        } else if (OSHelper.operatingSystem.contains("nix") || OSHelper.operatingSystem.contains("nux")) {
            downloadDir = "/home/"+OSHelper.whoami+"/.minecraft/mod";
            forge = "/home/" + OSHelper.whoami + "/.minecraft/config/forge.cfg";
        }else{
            System.err.println("Not Supported OS!");
            return;
        }

        if(Files.exists(Paths.get(forge))){
            System.out.println("Forge Installed: Yes");
        }else{
            System.err.println("Forge isnt installed");
            Scanner sc = new Scanner(System.in);
            System.out.println("U wanna install Forge? (yes/no)");
            String choice = sc.next();
            choice.toLowerCase();

            if(choice == "yes"){
            }else{
                System.out.println("Then Please install Forge by ur self");
            }
        }

        if (Files.exists(Paths.get(downloadDir))) {
            downloadFile(downloadDir);
        } else {
            Path down =  Paths.get(downloadDir);
            Files.createDirectories(down);
            System.err.println("The mods directory does not exist. Aborting download.");
        }

    }
}
