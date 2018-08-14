var ajax = function (url, method, data, async, cache, success, error) {
    $.ajax({
        url: url,
        type: method,
        dataType: "json",
        data: data,
        cache: cache,
        async: async,
        success: function (data, textStatus, jqXHR) {
            success(data, textStatus, jqXHR);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            error(XMLHttpRequest, textStatus, errorThrown);
        }
    });
};
function SHA512(str) {
    var SHA512 = new Hashes.SHA512;
    return SHA512.hex(str);
}