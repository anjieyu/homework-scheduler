package anjie.yu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class Main {
    public static AssignmentsRecord projectRecord = new AssignmentsRecord();

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Homework Scheduler");
        FlowLayout mainFrameLayout = new FlowLayout();
        mainFrame.setSize(1000, 800);
        mainFrame.setLayout(mainFrameLayout);
        mainFrame.setVisible(true);

        JPanel parentPanel = new JPanel();
        JPanel leftHandPanel = new JPanel();
        JPanel rightHandPanel = new JPanel();

        BoxLayout parentLayout = new BoxLayout(parentPanel, BoxLayout.X_AXIS);
        BoxLayout leftHandLayout = new BoxLayout(leftHandPanel, BoxLayout.Y_AXIS);
        BoxLayout rightHandLayout = new BoxLayout(rightHandPanel, BoxLayout.Y_AXIS);
        parentPanel.setLayout(parentLayout);
        leftHandPanel.setLayout(leftHandLayout);
        rightHandPanel.setLayout(rightHandLayout);

        JPanel inputParamsPanel = new JPanel();
        GridLayout inputParamsLayout = new GridLayout(4, 2);
        inputParamsPanel.setLayout(inputParamsLayout);

        JLabel l_assignmentName = new JLabel("Assignment Name: ");
        JLabel l_dueDate = new JLabel("Due Date: ");
        JLabel l_duration = new JLabel("Duration: ");
        JLabel l_subject = new JLabel("Subject: ");

        JTextField tf_assignmentName = new JTextField();
        JTextField tf_dueDate = new JTextField();
        JTextField tf_duration = new JTextField();
        JTextField tf_subject = new JTextField();

        inputParamsPanel.add(l_assignmentName);
        inputParamsPanel.add(tf_assignmentName);
        inputParamsPanel.add(l_dueDate);
        inputParamsPanel.add(tf_dueDate);
        inputParamsPanel.add(l_duration);
        inputParamsPanel.add(tf_duration);
        inputParamsPanel.add(l_subject);
        inputParamsPanel.add(tf_subject);

        leftHandPanel.add(inputParamsPanel);

        GridLayout processButtonsLayout = new GridLayout(2, 2, 2, 2);
        JPanel processButtonsPanel = new JPanel(processButtonsLayout);

        JButton b_hardestFirst = new JButton("Hardest First");
        JButton b_easiestFirst = new JButton("Easiest First");
        JButton b_soonestDue = new JButton("Soonest Due");
        JButton b_favoriteSubject = new JButton("Favorite Subject");

        processButtonsPanel.add(b_hardestFirst);
        processButtonsPanel.add(b_easiestFirst);
        processButtonsPanel.add(b_soonestDue);
        processButtonsPanel.add(b_favoriteSubject);

        rightHandPanel.add(processButtonsPanel);

        String[] columns = {"Name", "Due Date", "Duration", "Subject"};
        DefaultTableModel tm_homeworkListLeft = new DefaultTableModel(columns, 30);
        JTable t_homeworkListLeft = new JTable(tm_homeworkListLeft);
        JScrollPane sp_homeworkListLeft = new JScrollPane(t_homeworkListLeft);

        /**
         * Here we are going to put the sample assignments
         */

        Assignment a1 = new Assignment("Essay", "English", new Date(Date.parse("1/30/23")), 2.0);
        Vector<String> r1 = new Vector<>();
        r1.add(a1.getName());
        r1.add(a1.getDueDate().toString());
        r1.add(String.valueOf(a1.getDuration()));
        r1.add(a1.getSubject());
        tm_homeworkListLeft.insertRow(0, r1);
        projectRecord.addAssignment(a1);

        Assignment a2 = new Assignment("Homework", "Math", new Date(Date.parse("1/27/23")), 1.0);
        Vector<String> r2 = new Vector<>();
        r2.add(a2.getName());
        r2.add(a2.getDueDate().toString());
        r2.add(String.valueOf(a2.getDuration()));
        r2.add(a2.getSubject());
        tm_homeworkListLeft.insertRow(0, r2);
        projectRecord.addAssignment(a2);

        Assignment a3 = new Assignment("Report", "Science", new Date(Date.parse("1/29/23")), 3.0);
        Vector<String> r3 = new Vector<>();
        r3.add(a3.getName());
        r3.add(a3.getDueDate().toString());
        r3.add(String.valueOf(a3.getDuration()));
        r3.add(a3.getSubject());
        tm_homeworkListLeft.insertRow(0, r3);
        projectRecord.addAssignment(a3);

        Assignment a4 = new Assignment("Worksheet", "Spanish", new Date(Date.parse("2/1/23")), 0.5);
        Vector<String> r4 = new Vector<>();
        r4.add(a4.getName());
        r4.add(a4.getDueDate().toString());
        r4.add(String.valueOf(a4.getDuration()));
        r4.add(a4.getSubject());
        tm_homeworkListLeft.insertRow(0, r4);
        projectRecord.addAssignment(a4);

        Assignment a5 = new Assignment("Presentation", "History", new Date(Date.parse("2/2/23")), 4.0);
        Vector<String> r5 = new Vector<>();
        r5.add(a5.getName());
        r5.add(a5.getDueDate().toString());
        r5.add(String.valueOf(a5.getDuration()));
        r5.add(a5.getSubject());
        tm_homeworkListLeft.insertRow(0, r5);
        projectRecord.addAssignment(a5);

        Assignment a6 = new Assignment("Paper", "English", new Date(Date.parse("1/30/23")), 2.0);
        Vector<String> r6 = new Vector<>();
        r6.add(a6.getName());
        r6.add(a6.getDueDate().toString());
        r6.add(String.valueOf(a6.getDuration()));
        r6.add(a6.getSubject());
        tm_homeworkListLeft.insertRow(0, r6);
        projectRecord.addAssignment(a6);

        t_homeworkListLeft.repaint();
        sp_homeworkListLeft.repaint();

        /**
         * End of entering sample data
         */

        DefaultTableModel tm_homeworkListRight = new DefaultTableModel(columns, 30);
        JTable t_homeworkListRight = new JTable(tm_homeworkListRight);
        JScrollPane sp_homeworkListRight = new JScrollPane(t_homeworkListRight);

        leftHandPanel.add(sp_homeworkListLeft);
        rightHandPanel.add(sp_homeworkListRight);

        FlowLayout modifyButtonsLayout = new FlowLayout();
        JPanel modifyButtonsPanel = new JPanel(modifyButtonsLayout);

        JButton b_submit = new JButton("Submit");
        JButton b_delete = new JButton("Delete");

        b_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String assignmentName = tf_assignmentName.getText();
                String subject = tf_subject.getText();
                String date = tf_dueDate.getText();
                Double duration = Double.valueOf(tf_duration.getText());

                Vector<String> rowData = new Vector<>();
                rowData.add(assignmentName);
                rowData.add(date);
                rowData.add(String.valueOf(duration));
                rowData.add(subject);
                tm_homeworkListLeft.insertRow(0, rowData);

                projectRecord.addAssignment(new Assignment(assignmentName, subject, new Date(Date.parse(date)), duration));

                t_homeworkListLeft.repaint();
                sp_homeworkListLeft.repaint();

                tf_assignmentName.setText("");
                tf_subject.setText("");
                tf_dueDate.setText("");
                tf_duration.setText("");
            }
        });

        /**
         * Noted defect: cannot delete multiple items at once
         */
        b_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selected = t_homeworkListLeft.getSelectedRows();
                if (selected.length > 1) {
                    return;
                }
                Vector<Vector> rowData = tm_homeworkListLeft.getDataVector();
                Vector<Vector> rowDataRight = tm_homeworkListRight.getDataVector();
                for (int r : selected) {
                    String name = (String) rowData.get(r).get(0);
                    String duration = (String) rowData.get(r).get(2);
                    String subject = (String) rowData.get(r).get(3);
                    Double du = Double.valueOf(duration);

                    Assignment temp = projectRecord.getAssignment(name, subject, du);
                    if (temp != null) {
                        tm_homeworkListLeft.removeRow(r);
                        for (int i = 0; i < rowDataRight.size(); i++) {
                            if (rowDataRight.get(i).get(0).equals(name) && rowDataRight.get(i).get(3).equals(subject) && Objects.equals(rowDataRight.get(i).get(2), duration)) {
                                tm_homeworkListRight.removeRow(i);
                                break;
                            }
                        }
                        projectRecord.deleteAssignment(temp);
                    }
                }
                t_homeworkListLeft.repaint();
                sp_homeworkListLeft.repaint();
            }
        });

        modifyButtonsPanel.add(b_submit);
        modifyButtonsPanel.add(b_delete);
        leftHandPanel.add(modifyButtonsPanel);

        b_hardestFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector> rowData = tm_homeworkListRight.getDataVector();
                for (int i = 0; i < projectRecord.getAssignments().size(); i++) {
                    tm_homeworkListRight.removeRow(0);
                }
                t_homeworkListRight.repaint();
                sp_homeworkListRight.repaint();
                projectRecord.sortHardestFirst();
                for (Assignment a : projectRecord.getAssignments()) {
                    String assignmentName = a.getName();
                    String subject = a.getSubject();
                    String date = String.valueOf(a.getDueDate());
                    Double duration = a.getDuration();

                    Vector<String> row = new Vector<>();
                    row.add(assignmentName);
                    row.add(date);
                    row.add(String.valueOf(duration));
                    row.add(subject);
                    tm_homeworkListRight.insertRow(0, row);
                }
                t_homeworkListRight.repaint();
                sp_homeworkListRight.repaint();
            }
        });

        b_easiestFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector> rowData = tm_homeworkListRight.getDataVector();
                for (int i = 0; i < projectRecord.getAssignments().size(); i++) {
                    tm_homeworkListRight.removeRow(0);
                }
                t_homeworkListRight.repaint();
                sp_homeworkListRight.repaint();
                projectRecord.sortEasiestFirst();
                for (Assignment a : projectRecord.getAssignments()) {
                    String assignmentName = a.getName();
                    String subject = a.getSubject();
                    String date = String.valueOf(a.getDueDate());
                    Double duration = a.getDuration();

                    Vector<String> row = new Vector<>();
                    row.add(assignmentName);
                    row.add(date);
                    row.add(String.valueOf(duration));
                    row.add(subject);
                    tm_homeworkListRight.insertRow(0, row);
                }
                t_homeworkListRight.repaint();
                sp_homeworkListRight.repaint();
            }
        });

        b_soonestDue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector> rowData = tm_homeworkListRight.getDataVector();
                for (int i = 0; i < projectRecord.getAssignments().size(); i++) {
                    tm_homeworkListRight.removeRow(0);
                }
                t_homeworkListRight.repaint();
                sp_homeworkListRight.repaint();
                projectRecord.sortSoonestDue();
                for (Assignment a : projectRecord.getAssignments()) {
                    String assignmentName = a.getName();
                    String subject = a.getSubject();
                    String date = String.valueOf(a.getDueDate());
                    Double duration = a.getDuration();

                    Vector<String> row = new Vector<>();
                    row.add(assignmentName);
                    row.add(date);
                    row.add(String.valueOf(duration));
                    row.add(subject);
                    tm_homeworkListRight.insertRow(0, row);
                }
                t_homeworkListRight.repaint();
                sp_homeworkListRight.repaint();
            }
        });

        b_favoriteSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector> rowData = tm_homeworkListRight.getDataVector();
                for (int i = 0; i < projectRecord.getAssignments().size(); i++) {
                    tm_homeworkListRight.removeRow(0);
                }
                t_homeworkListRight.repaint();
                sp_homeworkListRight.repaint();
                projectRecord.sortFavoriteSubjects();
                for (Assignment a : projectRecord.getAssignments()) {
                    String assignmentName = a.getName();
                    String subject = a.getSubject();
                    String date = String.valueOf(a.getDueDate());
                    Double duration = a.getDuration();

                    Vector<String> row = new Vector<>();
                    row.add(assignmentName);
                    row.add(date);
                    row.add(String.valueOf(duration));
                    row.add(subject);
                    tm_homeworkListRight.insertRow(0, row);
                }
                t_homeworkListRight.repaint();
                sp_homeworkListRight.repaint();
            }
        });

        parentPanel.add(leftHandPanel);
        parentPanel.add(Box.createHorizontalStrut(5));
        parentPanel.add(new JSeparator(SwingConstants.VERTICAL));
        parentPanel.add(Box.createHorizontalStrut(5));
        parentPanel.add(rightHandPanel);
        mainFrame.add(parentPanel);

        mainFrame.setVisible(true);
    }
}