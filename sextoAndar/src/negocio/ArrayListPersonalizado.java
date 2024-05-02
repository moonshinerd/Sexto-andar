package negocio;
import java.util.ArrayList;

public class ArrayListPersonalizado<T> extends ArrayList<T> {
	@Override
	public String toString() {
        StringBuilder result = new StringBuilder("\n");
        for (int i = 0; i < size(); i++) {
            result.append(get(i));
            if (i < size() - 1) {
                result.append("------------------------------------");
            }
        }
        result.append("\n");
        return result.toString();
    }
}
