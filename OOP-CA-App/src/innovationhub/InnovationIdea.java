/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author AHMED MAHAT 25131923
 */
package innovationhub;

public class InnovationIdea {
    private String id;
    private String title;
    private String description;
    private String category;
    private double estimatedCost;
    private double feasibilityScore;
    private double impactScore;
    private double innovationScore;
    private double totalScore;
    private String status;

    public InnovationIdea() {
    }

    public InnovationIdea(String id, String title, String description,
                          String category, double estimatedCost,
                          double feasibilityScore, double impactScore,
                          double innovationScore, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.estimatedCost = estimatedCost;
        this.feasibilityScore = feasibilityScore;
        this.impactScore = impactScore;
        this.innovationScore = innovationScore;
        this.status = status;
    }

    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getEstimatedCost() { return estimatedCost; }
    public void setEstimatedCost(double estimatedCost) { this.estimatedCost = estimatedCost; }

    public double getFeasibilityScore() { return feasibilityScore; }
    public void setFeasibilityScore(double feasibilityScore) { this.feasibilityScore = feasibilityScore; }

    public double getImpactScore() { return impactScore; }
    public void setImpactScore(double impactScore) { this.impactScore = impactScore; }

    public double getInnovationScore() { return innovationScore; }
    public void setInnovationScore(double innovationScore) { this.innovationScore = innovationScore; }

    public double getTotalScore() { return totalScore; }
    public void setTotalScore(double totalScore) { this.totalScore = totalScore; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Simple CSV helpers (useful later for file I/O)
    public String toCsvString() {
        return id + "," + title + "," + description + "," + category + ","
                + estimatedCost + "," + feasibilityScore + ","
                + impactScore + "," + innovationScore + ","
                + totalScore + "," + status;
    }

    public static InnovationIdea fromCsvString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 10) {
            return null;
        }
        InnovationIdea idea = new InnovationIdea();
        idea.setId(parts[0]);
        idea.setTitle(parts[1]);
        idea.setDescription(parts[2]);
        idea.setCategory(parts[3]);
        idea.setEstimatedCost(Double.parseDouble(parts[4]));
        idea.setFeasibilityScore(Double.parseDouble(parts[5]));
        idea.setImpactScore(Double.parseDouble(parts[6]));
        idea.setInnovationScore(Double.parseDouble(parts[7]));
        idea.setTotalScore(Double.parseDouble(parts[8]));
        idea.setStatus(parts[9]);
        return idea;
    }

    @Override
    public String toString() {
        return id + " - " + title + " (Score: " + totalScore + ")";
    }
}
