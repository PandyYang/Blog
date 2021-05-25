<template>
  <div>
    <!-- banner -->
    <div class="about-banner banner">
      <h1 class="banner-title">关于我</h1>
    </div>
    <!-- 关于我内容 -->
    <v-card class="blog-container">
      <div class="my-wrapper">
        <v-avatar size="110">
          <img
            class="author-avatar"
            src="http://pandy-blog.oss-cn-beijing.aliyuncs.com/articles/1396828874708733953.jpg"
          />
        </v-avatar>
      </div>
      <div class="about-content markdown-body" v-html="aboutContent" />
    </v-card>
  </div>
</template>

<script>
export default {
  created() {
    this.getAboutContent();
  },
  data: function() {
    return {
      aboutContent: "哈哈哈"
    };
  },
  methods: {
    getAboutContent() {
      this.axios.get("/api/about").then(({ data }) => {
        const MarkdownIt = require("markdown-it");
        const md = new MarkdownIt();
        this.aboutContent = md.render(data.data);
      });
    }
  }
};
</script>

<style scoped>
.about-banner {
  background: url(https://pandy-blog.oss-cn-beijing.aliyuncs.com/articles/IMG_20210503_175610.jpg) center center / cover
    no-repeat;
  background-color: #49b1f5;
}
.about-content {
  word-break: break-word;
  line-height: 1.8;
  font-size: 14px;
}
.my-wrapper {
  text-align: center;
}
.author-avatar {
  transition: all 0.5s;
}
.author-avatar:hover {
  transform: rotate(360deg);
}
</style>
