import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.Binary;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Javamongo {

    public static void main(String[] args) {
        MongoClient mongoClient = null;
        try {
            // Connect to MongoDB (adjust host/port if necessary)
            mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("user_database");
            MongoCollection<Document> collection = database.getCollection("users");

            // Collect user details
            String name = JOptionPane.showInputDialog("Enter your name:");
            String idNumber = JOptionPane.showInputDialog("Enter your ID number:");
            String dateOfBirth = JOptionPane.showInputDialog("Enter your date of birth (YYYY-MM-DD):");
            String gender = JOptionPane.showInputDialog("Enter your gender:");

            // Validate inputs
            if (name == null || name.isEmpty() || idNumber == null || idNumber.isEmpty() ||
                dateOfBirth == null || dateOfBirth.isEmpty() || gender == null || gender.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required!");
                return;
            }

            // Check if ID already exists
            Document existingUser = collection.find(new Document("id_number", idNumber)).first();
            if (existingUser != null) {
                JOptionPane.showMessageDialog(null, "Error: ID number already exists!");
                return;
            }

            // Select and read the photo file
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a photo");
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File photoFile = fileChooser.getSelectedFile();
                byte[] photoBytes = readFileToByteArray(photoFile);

                // Create and insert the document
                Document userDocument = new Document("name", name)
                        .append("id_number", idNumber)
                        .append("date_of_birth", dateOfBirth)
                        .append("gender", gender)
                        .append("photo", new Binary(photoBytes));

                collection.insertOne(userDocument);
                JOptionPane.showMessageDialog(null, "User information saved successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No photo selected!");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading the photo file!");
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred!");
            e.printStackTrace();
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }

    // Helper method to read file into byte array (compatible with Java 7/8)
    private static byte[] readFileToByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            return data;
        }
    }
}

