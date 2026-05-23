## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Inline Validation and Layout Stability
**Learning:** Using `TextInputLayout`'s error property without `app:errorEnabled="true"` causes jarring layout jumps when errors appear. Additionally, leaving error messages visible after a user starts correcting the input creates a frustrating UX.
**Action:** Always set `app:errorEnabled="true"` on `TextInputLayout` to reserve error space and use `doOnTextChanged` listeners to clear error states as soon as the user begins typing.
