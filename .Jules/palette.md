## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Reducing Friction in Content Portability
**Learning:** For apps that generate unique user content (like QR codes or ID cards), relying on users to take screenshots adds unnecessary friction and variability in output quality.
**Action:** Implement native sharing capabilities using `FileProvider` to provide a direct, high-quality path for users to export and share app-generated content.
