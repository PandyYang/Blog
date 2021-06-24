<template>
  <v-app-bar app :class="navClass" hide-on-scroll flat height="60">
    <!-- 手机端导航栏 -->
    <div class="d-md-none nav-mobile-container">
      <div style="font-size:18px;font-weight:bold">
        <router-link to="/">
          Pandy
        </router-link>
      </div>
      <div style="margin-left:auto">
        <a @click="openSearch"><i class="iconfont iconsousuo"/></a>
        <a @click="openDrawer" style="margin-left:10px;font-size:20px">
          <i class="iconfont iconhanbao" />
        </a>
      </div>
    </div>
    <!-- 电脑导航栏 -->
    <div class="d-md-block d-none nav-container">
      <div class="float-left blog-title">
        <router-link to="/">
          Pandy
        </router-link>
      </div>
      <div class="float-right nav-title">
        <div class="menus-item">
          <router-link to="/">
            <i class="iconfont iconzhuye" style="color: black" />
            <span style="color:black;">首页</span>
          </router-link>
        </div>
        <div class="menus-item">
          <router-link to="/blog" style="color: black">
            <i style="color: black" class="iconfont iconguidang" />
            <span style="color:black;">写博客</span>
          </router-link>
        </div>
        <div class="menus-item">
          <router-link to="/bloglist" style="color: black">
            <i style="color: black" class="iconfont iconguidang" />
            <span style="color:black;">博客管理</span>
          </router-link>
        </div>
        <div class="menus-btn" style="color: black!important;">
          <a @click="openSearch" style="color: black"
            ><i style="color: black!important;" class="iconfont iconsousuo" />
            <span style="color:black;">搜索</span></a
          >
        </div>
        <div class="menus-item">
          <router-link to="/archives" style="color: black">
            <i class="iconfont iconguidang" style="color: black" />
            <span style="color:black;">归档</span>
          </router-link>
        </div>
        <div class="menus-item">
          <router-link to="/categories" style="color: black">
            <i class="iconfont iconfenlei" style="color: black" />
            <span style="color:black;">分类</span>
          </router-link>
        </div>
        <div class="menus-item">
          <router-link to="/tags" style="color: black">
            <i class="iconfont iconbiaoqian" style="color: black" />
            <span style="color:black;">标签</span>
          </router-link>
        </div>
        <!--        <div class="menus-item">-->
        <!--          <router-link to="/links">-->
        <!--            <i class="iconfont iconlianjie" /> 友链-->
        <!--          </router-link>-->
        <!--        </div>-->
        <!--        <div class="menus-item">-->
        <!--          <router-link to="/about">-->
        <!--            <i class="iconfont iconzhifeiji" /> 关于-->
        <!--          </router-link>-->
        <!--        </div>-->
        <div class="menus-item">
          <router-link to="/message" style="color: black">
            <i class="iconfont iconpinglunzu" style="color: black" />
            <span style="color:black;">留言</span>
          </router-link>
        </div>
        <div class="user-btn">
          <a
            v-if="!this.$store.state.avatar"
            @click="openLogin"
            style="color: black"
          >
            <i class="iconfont icondenglu" style="color: black" />
            <span style="color:black;">登录</span>
          </a>
          <template v-else>
            <img
              class="user-avatar"
              :src="this.$store.state.avatar"
              height="30"
              width="30"
            />
            <ul class="user-submenu">
              <li>
                <router-link to="/user">
                  <i class="iconfont icongerenzhongxin" />
                  <span style="color:black;">个人中心</span>
                </router-link>
              </li>
              <li>
                <a @click="logout"
                  ><i class="iconfont icontuichu" />
                  <span style="color:black;">退出</span></a
                >
              </li>
            </ul>
          </template>
        </div>
      </div>
    </div>
  </v-app-bar>
</template>

<script>
export default {
  mounted() {
    window.addEventListener("scroll", this.scroll);
  },
  data: function() {
    return {
      navClass: ""
    };
  },
  methods: {
    scroll() {
      const that = this;
      let scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;
      that.scrollTop = scrollTop;
      if (that.scrollTop > 60) {
        that.navClass = "nav-fixed";
      } else {
        that.navClass = "nav";
      }
    },
    openSearch() {
      this.$store.state.searchFlag = true;
    },
    openDrawer() {
      this.$store.state.drawer = true;
    },
    openLogin() {
      this.$store.state.loginFlag = true;
    },
    logout() {
      //如果在个人中心则跳回上一页
      if (this.$route.path == "/user") {
        this.$router.go(-1);
      }
      this.axios.get("/api/logout").then(({ data }) => {
        if (data.flag) {
          this.$store.commit("logout");
          this.$toast({ type: "success", message: data.message });
        } else {
          this.$toast({ type: "error", message: data.message });
        }
      });
    }
  },
  computed: {
    avatar() {
      return this.$store.state.avatar;
    }
  }
};
</script>

<style scoped>
i {
  margin-right: 4px;
}
ul {
  list-style: none;
}
.nav {
  background: rgba(0, 0, 0, 0) !important;
}
.nav a {
  color: #eee !important;
}
.nav .menus-item a {
  text-shadow: 0.05rem 0.05rem 0.1rem rgba(0, 0, 0, 0.3);
}
.nav .blog-title a {
  text-shadow: 0.1rem 0.1rem 0.2rem rgba(0, 0, 0, 0.15);
}
.theme--light.nav-fixed {
  background: rgba(255, 255, 255, 0.8) !important;
  box-shadow: 0 5px 6px -5px rgba(133, 133, 133, 0.6);
}
.theme--dark.nav-fixed {
  background: rgba(18, 18, 18, 0.8) !important;
}
.theme--dark.nav-fixed a {
  color: rgba(255, 255, 255, 0.8) !important;
}
.theme--light.nav-fixed a {
  color: #4c4948 !important;
}
.nav-fixed .menus-item a,
.nav-fixed .menus-btn a,
.nav-fixed .blog-title a {
  text-shadow: none;
}
.nav-container {
  font-size: 14px;
  width: 100%;
  height: 100%;
}
.nav-mobile-container {
  width: 100%;
  display: flex;
  align-items: center;
}
.blog-title,
.nav-title {
  display: flex;
  align-items: center;
  height: 100%;
}
.blog-title a {
  font-size: 18px;
  font-weight: bold;
}
.user-btn,
.menus-btn,
.menus-item {
  position: relative;
  display: inline-block;
  margin: 0 0 0 0.875rem;
}
.menus-btn a,
.menus-item a {
  transition: all 0.2s;
}
.nav-fixed .menus-btn a:hover,
.nav-fixed .menus-item a:hover {
  color: #49b1f5 !important;
}
.menus-item a:hover:after {
  width: 100%;
}
.menus-item a:after {
  position: absolute;
  bottom: -5px;
  left: 0;
  z-index: -1;
  width: 0;
  height: 3px;
  background-color: #80c8f8;
  content: "";
  transition: all 0.3s ease-in-out;
}
.user-btn a {
  transition: all 0.2s;
}
.user-avatar {
  cursor: pointer;
  border-radius: 50%;
}
.user-btn:hover .user-submenu {
  display: block;
}
.user-submenu {
  position: absolute;
  display: none;
  right: 0;
  width: max-content;
  margin-top: 8px;
  box-shadow: 0 5px 20px -4px rgba(0, 0, 0, 0.5);
  background-color: #fff;
  animation: submenu 0.3s 0.1s ease both;
}
.user-submenu:before {
  position: absolute;
  top: -8px;
  left: 0;
  width: 100%;
  height: 20px;
  content: "";
}
.user-submenu a {
  line-height: 2;
  color: #4c4948 !important;
  text-shadow: none;
  display: block;
  padding: 6px 14px;
}
.user-submenu a:hover {
  background: #4ab1f4;
}
@keyframes submenu {
  0% {
    opacity: 0;
    filter: alpha(opacity=0);
    transform: translateY(10px);
  }
  100% {
    opacity: 1;
    filter: none;
    transform: translateY(0);
  }
}
</style>
