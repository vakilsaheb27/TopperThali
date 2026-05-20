## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Robust Form Validation Pattern
**Learning:** Using generic `Toast` messages for validation is inaccessible and transient. A combination of `TextInputLayout.setError`, `app:errorEnabled="true"` (to prevent layout jumps), and `doOnTextChanged` (to clear errors instantly) provides a much more intuitive and accessible experience.
**Action:** Implement this three-part validation pattern (Set Error, Reserve Space, Auto-Clear) for all form inputs to ensure professional and accessible data entry.
