import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.util.*;

public class ChildrenMathLearningSystem {
    private static LearningSystem learningSystem = new LearningSystem();
    private static JFrame frame;
    private static String selectedProblemType = "Mixed"; // Default problem type

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame = new JFrame("Children Math Learning System");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600); // Set frame size
                frame.setResizable(false); // Make frame non-resizable
                frame.setLocationRelativeTo(null); // Center frame on screen
                showMainMenu(); // Show the main menu
                frame.setVisible(true); // Make the frame visible
            }
        });
    }

	private static void showMainMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(220, 240, 255)); // Light blue background
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40)); // Add padding

        JLabel title = new JLabel("Children Math Learning System");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton studentButton = createStyledButton("Login as Student");
        JButton teacherButton = createStyledButton("Login as Teacher");
        JButton settingsButton = createStyledButton("Settings");
        JButton loadButton = createStyledButton("Load Data");
        JButton saveButton = createStyledButton("Save Data");
        JButton exitButton = createStyledButton("Exit");

		// Add action listeners to the buttons
        studentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                loginUser("Student"); // Login as Student
            }
        });

        teacherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                loginUser("Teacher"); // Login as Teacher
            }
        });

        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showSettingsMenu(); // Show settings menu
            }
        });

        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                loadProgress(); // Load saved progress
            }
        });

        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                saveProgress(); // Save current progress
            }
        });

        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });
		
		// Add components to the panel
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Add space between components
        panel.add(studentButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add space between buttons
        panel.add(teacherButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(settingsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(loadButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(saveButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(exitButton);
		
		// Set the content pane of the frame to the main menu panel
        frame.setContentPane(panel);
        frame.validate();
    }

	// Display the settings menu
    private static void showSettingsMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 235, 205)); // Light peach background
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                "Settings", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

        JLabel label = new JLabel("Select Problem Type:");
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Dropdown for selecting problem type
        String[] problemTypes = {"Mixed", "Addition", "Subtraction", "Multiplication", "Division"};
        final JComboBox problemTypeBox = new JComboBox(problemTypes);
        problemTypeBox.setFont(new Font("Arial", Font.PLAIN, 20));
		problemTypeBox.setPreferredSize(new Dimension(250, 40));
		problemTypeBox.setMaximumSize(new Dimension(250, 40));
        problemTypeBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton saveButton = createStyledButton("Save");
        JButton backButton = createStyledButton("Back");

		// Save button action listener
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                selectedProblemType = (String) problemTypeBox.getSelectedItem(); // Save the selected problem type
                JOptionPane.showMessageDialog(frame, "Settings saved.");
                showMainMenu(); // Return to main menu
            }
        });
		
		 // Back button action listener
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showMainMenu(); // Return to main menu
            }
        });

		// Add components to the panel
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(problemTypeBox);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(saveButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(backButton);
		
		// Set the content pane of the frame to the settings menu panel
        frame.setContentPane(panel);
        frame.validate();
    }

	// Create a styled button with a consistent look and feel
    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial ", Font.PLAIN, 26));
        button.setBackground(new Color(70, 130, 180)); // Steel blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 40)); // Set fixed button size
        return button;
    }

	// Handle user login for both students and teachers
	private static void loginUser(String role) {
		String name = JOptionPane.showInputDialog(frame, "Enter " + role + " Name:");
		if (name != null && !name.trim().isEmpty()) { // Validate non-empty input
			User user = learningSystem.getUser(name); // Check if user already exists

			if (user != null) { // User exists
				// Check if the role matches the existing user's role
				if ((role.equals("Student") && user instanceof Teacher) || 
					(role.equals("Teacher") && user instanceof Student)) {
					JOptionPane.showMessageDialog(frame, 
						"This name is already registered as a " + 
						(user instanceof Teacher ? "Teacher" : "Student") + ".", 
						"Role Mismatch", JOptionPane.ERROR_MESSAGE);
					return;
				}
			} else { // Create a new user if it doesn't exist
				if (role.equals("Student")) {
					user = new Student(name);
				} else if (role.equals("Teacher")) {
					user = new Teacher(name);
				} else {
					return; // Invalid role
				}
				learningSystem.addUser(user); // Add the new user
			}

			// Redirect based on role
			if (role.equals("Student")) {
				showProblemSolvingScreen(user);
			} else if (role.equals("Teacher")) {
				showTeacherDashboard((Teacher) user);
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Name cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	// Display the teacher dashboard with student results
	private static void showTeacherDashboard(final Teacher teacher) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(240, 248, 255)); // Light blue
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
				"Teacher Dashboard", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

		JLabel title = new JLabel("Student Results");
		title.setFont(new Font("Arial", Font.BOLD, 32));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);

		JTextArea studentList = new JTextArea(15, 40); // Area for results
		studentList.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		studentList.setEditable(false);

		StringBuilder data = new StringBuilder();
		for (User user : learningSystem.getAllUsers()) {
			if (user instanceof Student) {
				data.append(String.format("%-20s Score: %d%n", user.getName(), user.getScore()));
			}
		}
		studentList.setText(data.toString());

		JScrollPane scrollPane = new JScrollPane(studentList); // Add a scroll bar
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JButton backButton = createStyledButton("Back");
		JButton renameButton = createStyledButton("Rename User");
		JButton resetScoreButton = createStyledButton("Reset User Score");

		renameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String oldName = JOptionPane.showInputDialog(frame, "Enter the current name of the user:");
				String newName = JOptionPane.showInputDialog(frame, "Enter the new name for the user:");

				if (oldName != null && newName != null) {
					boolean result = learningSystem.updateUserName(oldName.trim(), newName.trim());
					if (result) {
						JOptionPane.showMessageDialog(frame, "User renamed successfully.");
					} else {
						JOptionPane.showMessageDialog(frame, "Renaming failed. Name might not exist or is already in use.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					showTeacherDashboard(teacher);
				}
			}
		});

		resetScoreButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String name = JOptionPane.showInputDialog(frame, "Enter the name of the user whose score you want to reset:");

				if (name != null) {
					boolean result = learningSystem.resetUserScore(name.trim());
					if (result) {
						JOptionPane.showMessageDialog(frame, "User score reset successfully.");
					} else {
						JOptionPane.showMessageDialog(frame, "Reset failed. User not found.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					showTeacherDashboard(teacher);
				}
			}
		});

		backButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				showMainMenu();
			}
		});

		panel.add(title);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(scrollPane);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(renameButton);
		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		panel.add(resetScoreButton);
		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		panel.add(backButton);

		frame.setContentPane(panel);
		frame.validate();
	}

	private static void showProblemSolvingScreen(final User user) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(255, 228, 196)); // Bisque background
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
				"Problem Solving", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

		final MathProblem problem = createProblem();
		problem.generateProblem();

		JLabel title = new JLabel("Solve the Problem");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel problemLabel = new JLabel(problem.getProblemStatement());
		problemLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		problemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		final JTextField answerField = new JTextField(10);
		answerField.setMaximumSize(new Dimension(200, 30));
		answerField.setFont(new Font("Arial", Font.PLAIN, 16));
		answerField.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton submitButton = createStyledButton("Submit");
		JButton backButton = createStyledButton("Back");

		submitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					int answer = Integer.parseInt(answerField.getText());
					if (problem.checkAnswer(answer)) {
						user.incrementScore();
						JOptionPane.showMessageDialog(frame, "Correct! Score: " + user.getScore(), "Success",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frame, "Incorrect! Try Again.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					showProblemSolvingScreen(user); // Reload a new problem
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Input Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		backButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				showMainMenu();
			}
		});

		panel.add(title);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(problemLabel);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(answerField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(submitButton);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(backButton);

		frame.setContentPane(panel);
		frame.validate();
	}


	private static MathProblem createProblem() {
		if (selectedProblemType.equals("Addition")) {
			return new AdditionProblem();
		} else if (selectedProblemType.equals("Subtraction")) {
			return new SubtractionProblem();
		} else if (selectedProblemType.equals("Multiplication")) {
			return new MultiplicationProblem();
		} else if (selectedProblemType.equals("Division")) {
			return new DivisionProblem();
		} else { // Mixed problem type
			double random = Math.random();
			if (random < 0.25) {
				return new AdditionProblem();
			} else if (random < 0.5) {
				return new SubtractionProblem();
			} else if (random < 0.75) {
				return new MultiplicationProblem();
			} else {
				return new DivisionProblem();
			}
		}
	}

	private static void saveProgress() {
		learningSystem.saveData("progress.dat");
		JOptionPane.showMessageDialog(frame, "Data saved successfully.");
	}

	private static void loadProgress() {
		learningSystem.loadData("progress.dat");
		JOptionPane.showMessageDialog(frame, "Data loaded successfully.");
	}
}
