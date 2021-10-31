public class Video implements Comparable<Video> {
    private final String titleId;
    // a tconst, an alphanumeric unique identifier of the title
    private final int ordering;
    // a number to uniquely identify rows for a given titleId
    private final String title;
    // the localized title
    private final String region;
    // the region for this version of the title
    private final String language;
    // the language of the title
    private final String[] types;
    /* Enumerated set of attributes for this alternative title. One or more of the following:
       "alternative", "dvd", "festival", "tv", "video", "working", "original", "imdbDisplay".
       New values may be added in the future without warning */
    private final String[] attributes;
    // Additional terms to describe this alternative title, not enumerated
    private final boolean isOriginalTitle;
    // 0: not original title; 1: original title

    public Video(String titleId, int ordering, String title, String region, String language,
                 String[] types, String[] attributes, boolean isOriginalTitle) {
        this.titleId = titleId;
        this.ordering = ordering;
        this.title = title;
        this.region = region;
        this.language = language;
        this.types = types;
        this.attributes = attributes;
        this.isOriginalTitle = isOriginalTitle;
    }

    public String getTitleId() {
        return titleId;
    }

    @Override
    public String toString() {
        return "Video{" +
                "\n\ttitleId= " + titleId +
                "\n\tordering= " + ordering +
                "\n\ttitle= " + title +
                "\n\tregion= " + region +
                "\n\tlanguage= " + language +
                "\n\ttypes= " + String.join(", ", types) +
                "\n\tattributes= " + String.join(", ", attributes) +
                "\n\tisOriginalTitle= " + isOriginalTitle +
                "\n}";
    }

    @Override
    public int compareTo(Video other) {
        Rating rating1 = Server.getRating(other.titleId);
        Rating rating2 = Server.getRating(this.titleId);

        double s1 = rating1.getAverageRating();
        double s2 = rating2.getAverageRating();

        if (s1 != s2) {
            // compare by score
            return (s1 > s2) ? 1 : -1;
        } else {
            // compare by numVotes
            int v1 = rating1.getNumVotes();
            int v2 = rating2.getNumVotes();
            return v1 - v2;
        }

//        return Double.compare(
//                Server.getRating(other.titleId).getAverageRating(),
//                Server.getRating(this.titleId).getAverageRating());
    }
}
