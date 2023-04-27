package adamon.model;

public class TesteJBDC {
            public static void main(String[] args) {
                try {
                    Class.forName("org.postgresql.Driver");
                    System.out.println("PostgreSQL JDBC encontrado!");
                } catch (ClassNotFoundException e) {
                    System.out.println("PostgreSQL JDBC não encontrado!");
                }
            }
        }

