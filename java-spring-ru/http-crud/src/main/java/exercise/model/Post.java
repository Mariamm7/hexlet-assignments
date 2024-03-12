package exercise.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Post {
    @Getter
    private String id;
    private String title;
    private String body;
}
