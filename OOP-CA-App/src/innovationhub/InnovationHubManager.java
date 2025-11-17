/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author AHMED MAHAT -25131923
 */
package innovationhub;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class InnovationHubManager {

    private ArrayList<InnovationIdea> ideas;
    private IdeaScoreCalculator scoreCalculator;

    public InnovationHubManager() {
        ideas = new ArrayList<>();
        scoreCalculator = new IdeaScoreCalculator();
    }

    public void addIdea(InnovationIdea idea) {
        double score = scoreCalculator.calculateScore(idea);
        idea.setTotalScore(score);
        ideas.add(idea);
    }

    public ArrayList<InnovationIdea> getAllIdeas() {
        return ideas;
    }

    public InnovationIdea searchByTitle(String title) {
        for (InnovationIdea idea : ideas) {
            if (idea.getTitle().equalsIgnoreCase(title)) {
                return idea;
            }
        }
        return null;
    }

    public boolean deleteIdea(String id) {
        for (int i = 0; i < ideas.size(); i++) {
            if (ideas.get(i).getId().equalsIgnoreCase(id)) {
                ideas.remove(i);
                return true;
            }
        }
        return false;
    }

    public void sortByScoreDescending() {
        Collections.sort(ideas, new Comparator<InnovationIdea>() {
            @Override
            public int compare(InnovationIdea a, InnovationIdea b) {
                return Double.compare(b.getTotalScore(), a.getTotalScore());
            }
        });
    }

    public int getIdeaCount() {
        return ideas.size();
    }

    // File I/O
    public void saveToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (InnovationIdea idea : ideas) {
                writer.write(idea.toCsvString());
                writer.newLine();
            }
        }
    }

    public void loadFromFile(String fileName) throws IOException {
        ideas.clear();
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                InnovationIdea idea = InnovationIdea.fromCsvString(line);
                if (idea != null) {
                    double score = scoreCalculator.calculateScore(idea);
                    idea.setTotalScore(score);
                    ideas.add(idea);
                }
            }
        }
    }
}
