## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Input Flow and Layout Stability
**Learning:** Automatically requesting focus on the primary input field (`etName.requestFocus()`) and using `app:errorEnabled="true"` on `TextInputLayout` creates a smoother, more stable form entry experience by eliminating extra taps and layout "jumps" during validation.
**Action:** Always request focus on the first input field in new forms and reserve error space in XML to ensure visual stability.
