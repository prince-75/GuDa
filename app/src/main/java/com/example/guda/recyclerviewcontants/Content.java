//定义一个实体类Contents，用于提供ListView和RecyclerView相同的的实体类型（这里是一张图加一句文字），实例代码在FourActivity类
package com.example.guda.recyclerviewcontants;

public class Content {
    private  String name;
    private int imageId;
    public Content(String name, int imageId){
        this.name=name;//名字
        this.imageId=imageId;//对应的图片
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
