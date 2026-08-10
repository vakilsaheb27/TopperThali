## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-20 - Decorative Emojis and Theme-Aware Text Contrast
**Learning:** Decorative emojis in custom Android empty state views can confuse screen readers if not specifically hidden, and hardcoded colors degrade dark mode usability.
**Action:** Always set `android:importantForAccessibility="no"` on TextViews rendering decorative emoji symbols, and use theme-aware text colors (`?android:attr/textColorPrimary` / `?android:attr/textColorSecondary`) to ensure consistent theme and dark mode contrast.
