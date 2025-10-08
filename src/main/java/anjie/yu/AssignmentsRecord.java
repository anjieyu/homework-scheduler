package anjie.yu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Logger;

public class AssignmentsRecord {
    public List<Assignment> assignments;
    public List<String> subjects;
    public Date currentDate;
    public List<String> favoriteSubjects;

    public AssignmentsRecord () {
        assignments = new ArrayList<>();
        subjects = new ArrayList<>();
        currentDate = new Date();
        favoriteSubjects = new ArrayList<>();
        setInitialFavoriteSubjects(new File("C:\\Users\\Chien-Ching Yu\\IdeaProjects\\HomeworkScheduler\\src\\main\\java\\anjie\\yu\\favorite_subjects.txt"));
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public List<String> getFavoriteSubjects() {
        return favoriteSubjects;
    }

    private void setInitialFavoriteSubjects(File initialFile) {
        try {
            Scanner reader = new Scanner(initialFile);
            while (reader.hasNextLine()) {
                String subject = reader.nextLine();
                favoriteSubjects.add(subject);
            }
            reader.close();
        } catch (FileNotFoundException nfe) {
            System.out.println("Could not open file for reading favorite subjects.");
        }
    }

    public Assignment getAssignment(String name, String subject, Double duration) {
        for (Assignment a : assignments) {
            if (a.getName().equals(name) && a.getSubject().equals(subject) && Objects.equals(a.getDuration(), duration)) {
                return a;
            }
        }
        return null;
    }

    public void addAssignment(Assignment a) {
        assignments.add(a);
        if (!subjects.contains(a.getSubject())) {
            subjects.add(a.getSubject());
        }
    }

    public void deleteAssignment(Assignment a) {
        assignments.remove(a);
        if (subjects.contains(a.getSubject())) {
            subjects.remove(subjects.stream().findFirst());
        }
    }

    public void sortHardestFirst() {
        sortCompareHardest(assignments);
    }

    public void sortEasiestFirst() {
        sortCompareEasiest(assignments);
    }

    public void sortSoonestDue() {
        sortCompareDueDate(assignments);
    }

    /**
     * Noted defect: sortFavoriteSubjects does not sort perfectly for
     * duplicated assignments (swapping bug)
     *
     * Another noted defect: if one assignment was deleted and then sorted by
     * another algorithm and then sorted again by favorites it may be out of order
     * (somewhat reproducible)
     */
    public void sortFavoriteSubjects() {
        int subjectsSorted = 0;
        int numDuplicates = 0;
        boolean subjectMatched = false;
        boolean hasSwapped = false;
        for (int i = 0; i < favoriteSubjects.size(); i++) {
            hasSwapped = false;
            if (subjectMatched) {
                subjectsSorted++;
                subjectMatched = false;
            }
            for (int j = 0; j < assignments.size(); j++) {
                if (assignments.get(j).getSubject().toLowerCase().equals(favoriteSubjects.get(i).toLowerCase())) {
                    subjectMatched = true;
                    Collections.swap(assignments, subjectsSorted + numDuplicates, j);
                    if (hasSwapped) {
                        numDuplicates++;
                    }
                    hasSwapped = true;
                }
            }
        }
        Collections.reverse(assignments);
    }

    private void sortCompareHardest(List<Assignment> a) {
        Collections.sort(a, new Comparator<Assignment>() {
            @Override
            public int compare(Assignment a1, Assignment a2) {
                return a1.getDuration().compareTo(a2.getDuration());
            }
        });
    }

    private void sortCompareEasiest(List<Assignment> a) {
        Collections.sort(a, new Comparator<Assignment>() {
            @Override
            public int compare(Assignment a1, Assignment a2) {
                return a2.getDuration().compareTo(a1.getDuration());
            }
        });
    }

    private void sortCompareDueDate(List<Assignment> a) {
        Collections.sort(a, new Comparator<Assignment>() {
            @Override
            public int compare(Assignment a1, Assignment a2) {
                return a2.getDueDate().compareTo(a1.getDueDate());
            }
        });
    }
}
