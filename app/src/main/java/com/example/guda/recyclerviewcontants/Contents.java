//定义一个实体类Contents，用于提供ListView和RecyclerView相同的的实体类型（这里是一张图加一句文字），实例代码在FourActivity类
package com.example.guda.recyclerviewcontants;

public class Contents {
    private  String name;
    private String uri;
    private int imageId;
    public Contents(String name,int imageId, String uri){
        this.name=name;//标题
        this.imageId=imageId;//对应的图片
        this.uri=uri;//网址
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
    public String getUri() {return uri; }
}
