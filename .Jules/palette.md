## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2026-06-13 - Comprehensive Form Validation Feedback
**Learning:** When using TextInputLayouts for form validation, providing feedback for all invalid fields simultaneously is superior to short-circuiting after the first error. It allows users to see the full scope of required corrections at once.
**Action:** Implement a validation flag and check all input fields before returning from a submission click listener.
