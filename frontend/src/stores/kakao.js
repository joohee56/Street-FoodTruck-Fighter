import { defineStore } from "pinia";
import RF from "@/api/RF";
import axios from "axios";

export const useKakaoStore = defineStore("Kakao", {
  state: () => {
    const scheduleData = {
      date:'',
      openTime:'',
      closeTime:'',
      address:'',
    }
    return {
      s_markers_info: [
        ["밥",36.36880618678187,127.37618869404398],
        ["밥",36.371081876305944,127.36300597228991],
        ["밥",36.37105797918127,127.34678120029335],
        ["밥",36.35475430283077,127.35036311468708],
        ["밥",36.3663566167986,127.32661443116633],
        ["밥",36.387704400721304,127.34926257984003],
        ["밥",36.379838042140285,127.37570687933562],
        ["밥",36.35463108316967,127.36738596128184],
        ["밥",36.35961466250694,127.33995628313765],
        ["밥",36.354286844772105,127.33743754789877],
        ["잉어",36.380344124582635,127.32613796760278],
        ["잉어",36.3590214384901,127.31980968379185],
        ["잉어",36.34771471725568,127.34150870343409],
        ["잉어",36.34883310673394,127.37716204023228],
        ["잉어",36.3538976370704,127.39394231325642],
        ["잉어",36.375325209657895,127.38914701779225],
        ["잉어",36.388875952062065,127.36727951717582]
      ],
      currentAddress:'',
      scheduleData,
    }
  },
  actions: {
    setSurvey() {
      const token = localStorage.getItem("token");
      axios({
        url: RF.foodtruck.survey(),
        method: "post",
        headers: { Authorization: "Bearer" + token },
      })
        .then((res) => {
          alert(res.data);
        })
        .catc((err) => {
          console.log(err);
        });
    },
    getSurvey() {
      const token = localStorage.getItem("token");
      axios({
        url: RF.foodtruck.survey(),
        method: "get",
        headers: { Authorization: "Bearer" + token },
      })
        .then((res) => {
          alert(res.data);
        })
        .catc((err) => {
          console.log(err);
        });
    },
    

  }
})