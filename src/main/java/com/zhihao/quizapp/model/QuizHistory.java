package com.zhihao.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "QuizHistory")  // 此表名仅用于映射，不会实际存在于数据库中
@SqlResultSetMapping(
        name = "QuizHistoryMapping",
        classes = {
                @ConstructorResult(
                        targetClass = QuizHistory.class,
                        columns = {
                                @ColumnResult(name = "QUIZ_ID", type = Integer.class),
                                @ColumnResult(name = "QUIZNAME", type = String.class),
                                @ColumnResult(name = "CATEGORYNAME", type = String.class),
                                @ColumnResult(name = "STARTTIME", type = String.class),
                                @ColumnResult(name = "ENDTIME", type = String.class),
                                @ColumnResult(name = "SCORE", type = Integer.class),
                                @ColumnResult(name = "RESULT", type = String.class),
                                @ColumnResult(name = "USERNAME", type = String.class)
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

    public QuizHistory(int quizId, String quizName, String categoryName, String startTime, String endTime, int score, String result, String userName) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.categoryName = categoryName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.score = score;
        this.result = result;
        this.userName = userName;
    }
}
