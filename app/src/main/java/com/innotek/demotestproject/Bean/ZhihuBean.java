package com.innotek.demotestproject.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/7/21.
 */

public class ZhihuBean  implements Serializable{

    public String date;

    public List<Storie> stories;

    public List<Top_storie> top_stories;

    public  static class Storie implements  Serializable{

        public String prefix;
        public int id ;
        public String[] images;
        public String title;
        public int type;

    }
    public  static class Top_storie  implements  Serializable{
        public String prefix;
        public int id ;
        public String  images;
        public String title;
        public int type;
    }
}
