console.log('main.js loaded!');

document.addEventListener('DOMContentLoaded', function() {
    // 데이터 속성을 사용한 방법
    const genres = ['action', 'romance', 'comedy', 'horror', 'thriller']; // 실제 존재하는 장르들
    
    genres.forEach(genre => {
        const swiperElement = document.querySelector('.swiper-' + genre);
        
        if (swiperElement && swiperElement.querySelector('.swiper-slide')) {
            console.log('Found swiper for genre:', genre);
            
            // 이미 초기화되었는지 확인
            if (!swiperElement.classList.contains('swiper-initialized')) {
                console.log('Initializing Swiper for:', genre);
                
                new Swiper('.swiper-' + genre, {
                    slidesPerView: 3,
                    spaceBetween: 20,
                    freeMode: false,
                    navigation: {
                        nextEl: '.nextbtn-' + genre,
                        prevEl: '.prevbtn-' + genre,
                    },
                    breakpoints: {
                        320: {
                            slidesPerView: 2,
                            spaceBetween: 10
                        },
                        640: {
                            slidesPerView: 3,
                            spaceBetween: 15
                        },
                        1024: {
                            slidesPerView: 3,
                            spaceBetween: 20
                        }
                    }
                });
            }
        }
    });
});