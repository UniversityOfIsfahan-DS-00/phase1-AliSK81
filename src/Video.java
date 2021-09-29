public class Video {
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
}
