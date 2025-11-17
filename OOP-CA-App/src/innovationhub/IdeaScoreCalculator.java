/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author AHMED MAHAT -25131923
 */
package innovationhub;

public class IdeaScoreCalculator {

    private double feasibilityWeight;
    private double impactWeight;
    private double costWeight;

    public IdeaScoreCalculator() {
        this.feasibilityWeight = 0.4;
        this.impactWeight = 0.4;
        this.costWeight = 0.2;
    }

    public IdeaScoreCalculator(double feasibilityWeight,
                               double impactWeight,
                               double costWeight) {
        this.feasibilityWeight = feasibilityWeight;
        this.impactWeight = impactWeight;
        this.costWeight = costWeight;
    }

    public double calculateScore(InnovationIdea idea) {
        double feasibility = idea.getFeasibilityScore();
        double impact = idea.getImpactScore();
        double innovation = idea.getInnovationScore();
        double cost = idea.getEstimatedCost();

        // Very simple cost -> score conversion (placeholder)
        double costScore = 10 - Math.min(cost / 10_000.0, 10);

        double total = feasibilityWeight * feasibility
                     + impactWeight * impact
                     + costWeight * costScore;

        
        total += 0.1 * innovation;

        return total;
    }

    // Overloaded version 
    public double calculateScore(double feasibility, double impact,
                                 double innovation, double cost) {
        InnovationIdea tmp = new InnovationIdea();
        tmp.setFeasibilityScore(feasibility);
        tmp.setImpactScore(impact);
        tmp.setInnovationScore(innovation);
        tmp.setEstimatedCost(cost);
        return calculateScore(tmp);
    }

    // Getters/setters 
    public double getFeasibilityWeight() { return feasibilityWeight; }
    public void setFeasibilityWeight(double feasibilityWeight) { this.feasibilityWeight = feasibilityWeight; }

    public double getImpactWeight() { return impactWeight; }
    public void setImpactWeight(double impactWeight) { this.impactWeight = impactWeight; }

    public double getCostWeight() { return costWeight; }
    public void setCostWeight(double costWeight) { this.costWeight = costWeight; }
}
