## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2026-06-27 - Simultaneous Form Validation
**Learning:** Checking all form fields and displaying all relevant error messages at once (the "Validation UX Pattern") is superior to short-circuiting on the first error. It allows users to fix all issues in a single pass, reducing frustration.
**Action:** Implement a validation flag and check every field before returning, rather than returning as soon as one error is found. Pair this with `TextWatcher`s to clear errors as the user corrects them.
