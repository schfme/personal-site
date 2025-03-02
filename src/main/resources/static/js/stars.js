// stars.js

(() => {
  const createStar = () => {
    const star = document.createElement('div');
    star.classList.add('star');

    const { innerWidth, innerHeight } = window;
    const x = Math.random() * innerWidth;
    const y = Math.random() * innerHeight;
    const size = Math.random() * 3 + 1;
    const opacity = Math.random() * 0.8 + 0.2;

    Object.assign(star.style, {
      width: `${size}px`,
      height: `${size}px`,
      opacity,
      left: `${x}px`,
      top: `${y}px`
    });

    document.body.append(star);
    setTimeout(() => star.remove(), 1800);
  };

  const generateStars = () => setInterval(createStar, 50);

  document.addEventListener('DOMContentLoaded', generateStars);
})();
