import java.util.*;
import java.io.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Learning System to manage users
class LearningSystem {
    private List<User> users;

    public LearningSystem() {
        // Thread-safe list implementation using CopyOnWriteArrayList
        users = new CopyOnWriteArrayList<User>();
    }

    public synchronized void addUser(User user) {
        users.add(user); // Add a new user to the system
    }

    public synchronized User getUser(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user; // Return user if found
            }
        }
        return null; // Return null if no user found
    }

    public synchronized List<User> getAllUsers() {
        return new ArrayList<User>(users); // Return a copy to avoid external modification
    }
	
	// Check if a username is unique (case-insensitive)
	public synchronized boolean isUserNameUnique(String name) {
		for (User user : users) {
			if (user.getName().equalsIgnoreCase(name)) {
				return false; // Name is not unique
			}
		}
		return true; // Name is unique
	}
	
	// Update a user's name
	public synchronized boolean updateUserName(String oldName, String newName) {
        if (!isUserNameUnique(newName)) {
            return false; // New name is not unique
        }
        for (User user : users) {
            if (user.getName().equals(oldName)) {
                user.name = newName; // Update user name
                return true;
            }
        }
        return false; // Return false if user with old name not found
    }
	
	// Reset a user's score
    public synchronized boolean resetUserScore(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                user.score = 0; // Reset score to 0
                return true;
            }
        }
        return false; // Return false if user with name not found
    }
	
	// Save the list of users to a file
    public void saveData(String filename) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(users); // Write users to file
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + filename);
            e.printStackTrace(); // Log the error
        } finally {
            if (out != null) {
                try {
                    out.close(); // Close output stream
                } catch (IOException e) {
                    System.err.println("Error closing output stream: " + e.getMessage());
                }
            }
        }
    }

	// Load the list of users from a file
    @SuppressWarnings("unchecked")
    public void loadData(String filename) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            users = (List<User>) in.readObject(); // Read users from file
            if (users == null) {
                users = new CopyOnWriteArrayList<User>(); // Ensure list is initialized
            }
        } catch (IOException e) {
            System.err.println("Error loading data from file: " + filename);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found during data loading: " + e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close(); // Close input stream
                } catch (IOException e) {
                    System.err.println("Error closing input stream: " + e.getMessage());
                }
            }
        }
    }
}
