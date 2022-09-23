package DSA.Graphs;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Vertex    {
    String label;
    Vertex(String label){
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return label.equals(vertex.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}
