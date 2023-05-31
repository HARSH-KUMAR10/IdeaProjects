package bankserver.repo;

public interface OperationStructure
{
    Object create(String... args);

    Object read(String... args);

    Object update(String... args);

    Object delete(String... args);

}
