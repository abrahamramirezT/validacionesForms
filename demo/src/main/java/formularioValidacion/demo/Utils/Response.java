package formularioValidacion.demo.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Response <T>{
    T data;
    boolean error;
    int status;
    String message;
}
