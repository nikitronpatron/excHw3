import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите данные: ");
            String input = scanner.nextLine();
            String[] data = input.split(" ");
            if (data.length < 6) {
                throw new IllegalArgumentException("Введено недостаточное количество данных");
            } else if (data.length > 6) {
                throw new IllegalArgumentException("Введено слишком большое количество данных");
            }
            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String dateOfBirth = data[3];
            String phoneNumber = data[4];
            String gender = data[5];
            
            if (!isValidDate(dateOfBirth)) {
                throw new IllegalArgumentException("Неправильный формат даты рождения");
            }
            if (!isValidPhoneNumber(phoneNumber)) {
                throw new IllegalArgumentException("Неправильный формат номера телефона");
            }
            if (!isValidGender(gender)) {
                throw new IllegalArgumentException("Неправильный формат пола");
            }
            
            String filename = lastName + ".txt";
            String content = lastName + firstName + middleName + dateOfBirth + phoneNumber + gender;
            writeToFile(filename, content);
            
            System.out.println("Данные успешно записаны в файл " + filename);
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: ");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    
    private static boolean isValidDate(String date) {
        
        String[] parts = date.split("\\.");
        if (parts.length != 3) {
            return false;
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
    
        if (day < 1 || day > 31) {
            return false;
        }
    
        if (month < 1 || month > 12) {
            return false;
        }
    
        if (year < 1900 || year > 2050) {
            return false;
        }
    
        return true;
    }
    
    private static boolean isValidPhoneNumber(String phoneNumber) {    
            
        if (phoneNumber.length() != 7) {        
            return false;    
        }    
            try {        
                int number = Integer.parseInt(phoneNumber);    
            } 
            catch (NumberFormatException e) {        
                return false;    
            }    
            return true;
    }

    
    private static boolean isValidGender(String gender) {
        
        return gender.equals("f") || gender.equals("m");
    }
    
    private static void writeToFile(String filename, String content) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write(content);
            writer.newLine();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}