This package should only be used for repository implementations of repositories located in parent folder. And repository implementation should only be created with the purpose of avoiding creating native SQL statements if an UPDATE or DELETE SQL statement can't be created by following the JPQL syntax or when it is impossible to create a SELECT statement solely using JPQL.

NOTE:
If your SQL query utilizes the UNION [ALL] keyword, you MUST use native SQL.

Repository Implementation Template:

    @Repository
    public class <table-name>RepositoryImplementation {
    
        @PersistenceContext
        private EntityManager entityManager;
    
        // REPOSITORY METHODS... 
    }