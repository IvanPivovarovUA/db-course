package com.db.lab3;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Scanner;

@Service
public class Presentation {
    public boolean testUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter 'u' if you want to update:");
        String u = scanner.nextLine();
        if (u.equals("u") || u.equals("U")) {
            return true;
        }
        return false;
    }

    public ArrayList<String> inputWeatherAddress() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> address = new ArrayList<String>();

        System.out.println("Enter data to get weather forecast");
        System.out.print("Enter country: ");
        address.add(scanner.nextLine());
        System.out.print("Enter last_updated: ");
        address.add(scanner.nextLine());
        return address;
    }

    public void printWeatherForecast(String forecast) {
        System.out.println(forecast);
    }

    public boolean testExit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter 'y' if you want to exit the program:");
        String y = scanner.nextLine();
        if (y.equals("y") || y.equals("Y")) {
            return true;
        }
        return false;
    }

}
