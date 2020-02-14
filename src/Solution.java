import java.util.Map;
import java.util.LinkedHashMap;
/*
Обеспечить возможность клонирования объекта класса Solution используя глубокое клонирование.
Данные в карте users также должны быть клонированы.
Не забывать о методах equals и hashCode для корректного добавления элементов типа User в HashMap.


 */
public class Solution implements Cloneable {
    protected Map<String, User> users = new LinkedHashMap<>();
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Robert", new User(25, "Robert"));
        solution.users.put("Mike", new User(41, "Mike"));
        Solution clone = null;
        try {
            clone = (Solution) solution.clone();
            System.out.println(solution);
            System.out.println(clone);
            System.out.println(solution.users);
            System.out.println(clone.users);

            System.out.println(solution.equals(clone));
            System.out.println(solution.users.equals(clone.users));

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }
    @Override
    protected Solution clone() throws CloneNotSupportedException {
        Solution newObj = (Solution) super.clone();
        newObj.users = new LinkedHashMap<>();
        for (Map.Entry<String, User> setEn : users.entrySet()) {
            newObj.users.put(setEn.getKey(), (User) setEn.getValue().clone());
        }
        return newObj;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;
        Solution solution = (Solution) o;
        return users != null ? users.equals(solution.users) : solution.users == null;
    }
    @Override
    public int hashCode() {
        return users != null ? users.hashCode() : 0;
    }

    public static class User implements Cloneable {
        int age;
        String name;
        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }
        @Override
        protected User clone() throws CloneNotSupportedException {
            return new User(this.age, this.name);
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            User user = (User) o;
            if (age != user.age) return false;
            return name != null ? name.equals(user.name) : user.name == null;
        }
        @Override
        public int hashCode() {
            int result = age;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }

}

