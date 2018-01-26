package com.innotek.demotestproject.Bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

import com.google.gson.annotations.SerializedName;

/**
 *  TicketBean
 */
public class TicketBean implements Parcelable {

    /**
     * 优惠券ID
     */
    @SerializedName("ID")
    public String tickId;

    /**
     * 优惠券编号	/停车券编号
     */
   @SerializedName("CODE")
    public String ticketNo;

    /**
     * 券类型（1抵押券，2满减券，3折扣券）
     */
    @SerializedName(value = "TYPE"  )
    public String ticketType;

    /**券类型名称（1抵押券，2满减券，3折扣券）*/
    @SerializedName("TYPE_NAME")
    public String tickeTypeName;

    /**面额或折扣比例,优惠数值  8折：80，9折：90；满减、抵用*/
    @SerializedName("SALE_NUMBER")
    public String ticketMoneyDiscount;

    /***使用描述*/
    @SerializedName("TICKET_DESC")
    public String ticketDesc;

    /**优惠券名称	/停车券名称*/
    @SerializedName("TICKET_NAME")
    public String ticketName;

    /***优惠券获取时间*/
    @SerializedName("GET_TICKET_TIME")
    public String getTicketTime;

    /**停车券生效时间**/
    @SerializedName("EFFECTIVE_DATE")
    public String effectiveDate;

    /**截至日期,优惠券截至日期/停车券过期日期*/
    @SerializedName("EXPIRED_DATE")
    public String validityDate;

    /**最大折扣金额/最大优惠金额*/
    @SerializedName("MAX_SALE_AMOUNT")
    public String maxDiscountFee;

    /**最小可用金额**/
    @SerializedName("MIN_AVAILABLE_AMOUNT")
    public String minAvailableAmount;

    /**
     * 券状态	券状态（1未生效，2已生效，3.已使用，4即将过期，5已过期）
     */
    @SerializedName("STATUS")
    public String ticketStatus;

    /***是否默认，0：否；1：是**/
    @SerializedName("IS_DEFAULT")
    public int isDefault;


    public boolean isFrontFace = true;


  public TicketBean() {
    }


  @IntDef
  public @interface TicketStatus{

  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.tickId);
    dest.writeString(this.ticketNo);
    dest.writeString(this.ticketType);
    dest.writeString(this.tickeTypeName);
    dest.writeString(this.ticketMoneyDiscount);
    dest.writeString(this.ticketDesc);
    dest.writeString(this.ticketName);
    dest.writeString(this.getTicketTime);
    dest.writeString(this.effectiveDate);
    dest.writeString(this.validityDate);
    dest.writeString(this.maxDiscountFee);
    dest.writeString(this.minAvailableAmount);
    dest.writeString(this.ticketStatus);
    dest.writeInt(this.isDefault);
    dest.writeByte(this.isFrontFace ? (byte) 1 : (byte) 0);
  }

  protected TicketBean(Parcel in) {
    this.tickId = in.readString();
    this.ticketNo = in.readString();
    this.ticketType = in.readString();
    this.tickeTypeName = in.readString();
    this.ticketMoneyDiscount = in.readString();
    this.ticketDesc = in.readString();
    this.ticketName = in.readString();
    this.getTicketTime = in.readString();
    this.effectiveDate = in.readString();
    this.validityDate = in.readString();
    this.maxDiscountFee = in.readString();
    this.minAvailableAmount = in.readString();
    this.ticketStatus = in.readString();
    this.isDefault = in.readInt();
    this.isFrontFace = in.readByte() != 0;
  }

  public static final Creator<TicketBean> CREATOR = new Creator<TicketBean>() {
    @Override
    public TicketBean createFromParcel(Parcel source) {
      return new TicketBean(source);
    }

    @Override
    public TicketBean[] newArray(int size) {
      return new TicketBean[size];
    }
  };
}
