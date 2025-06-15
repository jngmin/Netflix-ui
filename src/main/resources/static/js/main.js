document.addEventListener('DOMContentLoaded', function() {
    const genres = ['thriler', 'romance', 'action', 'comedy', 'horror'];
    
    genres.forEach(genre => {
        const swiperElement = document.getElementById('swiper-' + genre);
        if (swiperElement && swiperElement.querySelector('.swiper-slide')) {
            new Swiper('#swiper-' + genre, {
                slidesPerView: 'auto', // 자동으로 보여질 슬라이드 수 계산
                spaceBetween: 10,
                freeMode: true,
                navigation: {
                    nextEl: '#next-' + genre,
                    prevEl: '#prev-' + genre,
                },
                breakpoints: {
                    // 반응형 설정
                    320: {
                        slidesPerView: 2,
                        spaceBetween: 10
                    },
                    640: {
                        slidesPerView: 3,
                        spaceBetween: 15
                    },
                    1024: {
                        slidesPerView: 4,
                        spaceBetween: 20
                    }
                }
            });
        }
    });
});