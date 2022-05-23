var formFile = new window.FormData();
formFile.append("files", fileObj);
axios.post("/filesUpload2TempPublicLib", formFile, {headers: { "Content-Type": "multipart/form-data" }})