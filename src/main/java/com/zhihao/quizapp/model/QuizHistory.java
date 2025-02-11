package com.zhihao.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-01 6:44 PM
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Immutable
@Table(name = "QuizHistory")
@SqlResultSetMapping(
        name = "QuizHistoryMapping",
        classes = {
                @ConstructorResult(
                        targetClass = QuizHistory.class,
                        columns = {
                                @ColumnResult(name = "quiz_id", type = Integer.class),
                                @ColumnResult(name = "quizName", type = String.class),
                                @ColumnResult(name = "categoryName", type = String.class),
                                @ColumnResult(name = "startTime", type = String.class),
                                @ColumnResult(name = "endTime", type = String.class),
                                @ColumnResult(name = "score", type = Integer.class),
                                @ColumnResult(name = "result", type = String.class),
                                @ColumnResult(name = "userName", type = String.class)
                        }
                )
        }
)
public class QuizHistory {
    @Id
    private int quizId;
    private String quizName;
    private String categoryName;
    private String startTime;
    private String endTime;
    private int score;
    private String result;
    private String userName;
}
