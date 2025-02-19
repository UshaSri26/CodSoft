import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp extends JFrame implements ActionListener {
    private String[][] questions = {
        {"What is 2 + 2?", "3", "4", "5", "6", "1"},
        {"What is the capital of France?", "Berlin", "Madrid", "Paris", "Rome", "2"},
        {"Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Saturn", "1"}
    };
    private int currentQuestionIndex = 0, score = 0, timeLeft = 10;
    private JLabel questionLabel, timerLabel;
    private JRadioButton[] options = new JRadioButton[4];
    private JButton nextButton;
    private Timer timer;
    private ButtonGroup group;

    public QuizApp() {
        setTitle("Quiz App with Timer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        timerLabel = new JLabel("Time Left: " + timeLeft + " sec");
        add(timerLabel);
        
        questionLabel = new JLabel();
        add(questionLabel);
        
        group = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            add(options[i]);
        }
        
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton);
        
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + " sec");
            if (timeLeft == 0) nextQuestion();
        });
        
        loadQuestion();
        setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestionIndex >= questions.length) {
            JOptionPane.showMessageDialog(this, "Quiz Over! Score: " + score + "/" + questions.length);
            System.exit(0);
        }
        
        timeLeft = 10;
        timerLabel.setText("Time Left: " + timeLeft + " sec");
        timer.restart();
        
        String[] q = questions[currentQuestionIndex];
        questionLabel.setText(q[0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(q[i + 1]);
            options[i].setSelected(false);
        }
    }

    private void nextQuestion() {
        timer.stop();
        String correctAnswer = questions[currentQuestionIndex][5];
        int selected = -1;
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selected = i;
                break;
            }
        }
        if (selected == Integer.parseInt(correctAnswer)) {
            score++;
        }
        currentQuestionIndex++;
        loadQuestion();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nextQuestion();
    }

    public static void main(String[] args) {
        new QuizApp();
    }
}
