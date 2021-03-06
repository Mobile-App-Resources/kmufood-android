package net.sproutlab.kmufood.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ApiResponse {
    @SerializedName("한울식당(법학관 지하1층)")
    @Expose
    public Map<String, Lawfood> lawFood;

    @SerializedName("학생식당(복지관 1층)")
    @Expose
    public Map<String, StuFood> stuFood;

    @SerializedName("교직원식당(복지관 1층)")
    @Expose
    public Map<String, StaffFood> staffFood;

    @SerializedName("청향(법학관 5층)")
    @Expose
    public Map<String, ChungFood> chungFood;

    @SerializedName("생활관식당 일반식(생활관 A동 1층)")
    @Expose
    public Map<String, DormFood1> dormFood1;

    @SerializedName("생활관식당 정기식(생활관 A동 1층)")
    @Expose
    public Map<String, DormFood2> dormFood2;
}
