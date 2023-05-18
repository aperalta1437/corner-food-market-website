This package should only be used for repository projections, which are used to retrieve specific columns from a table.

Repository projection example:

    public interface TfaDetails {
        String getTfaCode();
        long getTfaExpirationTime();
    }