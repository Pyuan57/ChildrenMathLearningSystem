# Children Math Learning System

The **Children Math Learning System** is an interactive Java desktop application designed to help elementary students sharpen their math skills. With exercises in addition, subtraction, multiplication, and division, this tool offers a fun way to learn, complete with instant feedback and progress tracking. It includes separate modes for students and teachers, making it ideal for both self-study and classroom use.

---

## Features
- **Math Practice:** Covers addition (up to 100), subtraction (positive results), multiplication (1-12 tables), and division (divisors 1-10).
- **Student Mode:** Solve problems, get real-time feedback, and track scores.
- **Teacher Mode:** View student progress, rename users, and reset scores via a dashboard.
- **Flexible Options:** Choose mixed problems or focus on a single operation.
- **Data Storage:** Save and load progress with file-based persistence.
- **User-Friendly:** Built with Java Swing and secured with thread-safe data handling.

---

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or later
- A desktop computer (Windows, macOS, or Linux)
- A terminal or command prompt

### Installation
1. **Download the Code:**  
   Get the source files from this repository or project folder.

2. **File Checklist:**  
   Ensure these 10 Java files are in one directory:  
   - `MathProblem.java`  
   - `AdditionProblem.java`  
   - `SubtractionProblem.java`  
   - `MultiplicationProblem.java`  
   - `DivisionProblem.java`  
   - `User.java`  
   - `Student.java`  
   - `Teacher.java`  
   - `LearningSystem.java`  
   - `ChildrenMathLearningSystem.java`  

3. **Compile:**  
   Open a terminal, navigate to the folder, and compile all files using:
   javac *.java

4. **Run:**  
Launch the program with:
java ChildrenMathLearningSystem

---

## How to Use It
1. **Main Menu:**  
- **Login as Student:** Enter your name to start solving problems.  
- **Login as Teacher:** Access the dashboard with a teacher name.  
- **Settings:** Pick a problem type (Mixed by default).  
- **Load/Save Data:** Restore or save your progress.  
- **Exit:** Close the app.  

2. **Student Mode:**  
- Log in, solve problems, and submit answers for instant feedback.  
- Click "Return" to stop, then save your progress.  

3. **Teacher Mode:**  
- Log in to see student scores.  
- Rename students or reset scores as needed.  
- Return to the main menu when done.  

---

## Why It’s Great
- **Instant Learning:** Feedback right after every answer.  
- **Simple Design:** Easy for kids and teachers to navigate.  
- **Efficient:** Saves time with automated tracking and management.  
- **Robust:** Uses Java Swing and `CopyOnWriteArrayList` for reliability.

---

## Limitations
- Single-device use only (no network support).  
- Local file storage (`progress.dat`).  
- Desktop-only application.
  
---

## Troubleshooting
- **Won’t Start?** Ensure all 10 `.java` files are present and compiled.  
- **No Feedback?** Enter numbers, not text, for answers.  
- **Missing Progress?** Load `progress.dat` from the menu.
