# Palette's Journal - Critical UX & Accessibility Learnings

## 2025-05-14 - Use TextInputLayout for better form accessibility
**Learning:** Using `TextInputLayout` with `TextInputEditText` ensures that labels remain visible even when the user is typing, which is crucial for accessibility and cognitive ease compared to basic `EditText` where hints disappear.
**Action:** Always wrap `TextInputEditText` with `TextInputLayout` in form screens.
