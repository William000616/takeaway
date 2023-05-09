<template>
    <van-swipe :autoplay="1000">
        <van-swipe-item v-for="(image, index) in images" :key="index">
            <img v-lazy="image" style="max-width: 100%;width: 80%;height: 150px" />
        </van-swipe-item>
    </van-swipe>
</template>

<script>
export default {
    name: "Swipe",
    data() {
        return {
            images: [
                '/upload/p5.png',
                '/upload/p6.png',
                '/upload/p7.png',

            ],
        };
    }
}
</script>

<style scoped></style>
