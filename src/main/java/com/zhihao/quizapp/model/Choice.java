package com.zhihao.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
/**
 * @TableName choice
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Choice")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id")
    private Integer choiceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;


    @Column(name = "description",columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "is_correct", nullable = false)
    private Integer isCorrect;
}