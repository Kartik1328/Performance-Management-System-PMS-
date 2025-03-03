//package com.pms.dto;
//
//public class OverAllRatingAndComments {
//    private String overallRating;
//    private String overallComments;
//
//    // Default constructor
//    public OverAllRatingAndComments() {}
//
//    // Getters and Setters
//    public String getOverallRating() {
//        return overallRating;
//    }
//
//    public void setOverallRating(String overallRating) {
//        this.overallRating = overallRating;
//    }
//
//    public String getOverallComments() {
//        return overallComments;
//    }
//
//    public void setOverallComments(String overallComments) {
//        this.overallComments = overallComments;
//    }
//
//    @Override
//    public String toString() {
//        return "OverAllRatingAndComments{" +
//                "overallRating='" + overallRating + '\'' +
//                ", overallComments='" + overallComments + '\'' +
//                '}';
//    }
//}

//
//
package com.pms.dto;
//
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class OverAllRatingAndComments {
    private String overallRating;
    private String overallComments;
    private String managerOverallRating;
    private String managerOverallComments;

}
