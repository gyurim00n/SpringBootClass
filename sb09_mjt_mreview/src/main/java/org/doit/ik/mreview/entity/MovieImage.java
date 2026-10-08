package org.doit.ik.mreview.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MovieImage {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long inum;
   private String uuid;
   private String imgName;
   private String path;
   
   // Movie 연관관계-   N:1 @ManyToOne
   @ToString.Exclude
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "mno") 
   private Movie movie;
}





