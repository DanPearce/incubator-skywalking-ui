package com.a.eye.skywalking.ui.controller;

import com.a.eye.skywalking.ui.service.TraceDagService;
import com.a.eye.skywalking.ui.web.ControllerBase;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pengys5
 */
@Controller
public class TraceDagController extends ControllerBase {
    private Logger logger = LogManager.getFormatterLogger(TraceDagController.class);

    @Autowired
    private TraceDagService service;

    @RequestMapping(value = "dagNodesLoad", method = RequestMethod.GET)
    @ResponseBody
    public void dagNodesLoad(@ModelAttribute("timeSliceType") String timeSliceType, @ModelAttribute("startTime") long startTime,
                             @ModelAttribute("endTime") long endTime, HttpServletResponse response) throws IOException {
        logger.debug("dagNodesLoad timeSliceType = %s, startTime = %s, endTime = %s", timeSliceType, startTime, endTime);
        JsonObject dagJson = service.buildGraphData(timeSliceType, startTime, endTime);
        reply(dagJson.toString(), response);
    }

    String aa = "{\n" +
            "    \"nodes\": [\n" +
            "        {\n" +
            "            \"id\": 0,\n" +
            "            \"label\": \"User\",\n" +
            "            \"image\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ\\nbWFnZVJlYWR5ccllPAAAA69pVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp\\nbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6\\neD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0\\nNTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo\\ndHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw\\ndGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wUmlnaHRzPSJodHRwOi8vbnMuYWRvYmUuY29tL3hh\\ncC8xLjAvcmlnaHRzLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9t\\nbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3Vy\\nY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcFJpZ2h0\\nczpNYXJrZWQ9IkZhbHNlIiB4bXBNTTpPcmlnaW5hbERvY3VtZW50SUQ9InV1aWQ6MzEzRkMzNjgz\\nODZGRTIxMUI2ODM4MDZDNTlBNjMzOEQiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6QUI4RjI2\\nOTBFNDdEMTFFMzhFMTQ4REY0NTBBMkU0OTgiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6QUI4\\nRjI2OEZFNDdEMTFFMzhFMTQ4REY0NTBBMkU0OTgiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhv\\ndG9zaG9wIENTNiAoV2luZG93cykiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJ\\nRD0ieG1wLmlpZDo0ODc2RDgzNDJBRUFFMzExQjVFQkQwQ0FBQTI2NzkwQSIgc3RSZWY6ZG9jdW1l\\n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\",\n" +
            "            \"instNum\": 0\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 1,\n" +
            "            \"label\": \"portal-service\",\n" +
            "            \"image\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ\\nbWFnZVJlYWR5ccllPAAAA69pVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp\\nbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6\\neD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0\\nNTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo\\ndHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw\\ndGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wUmlnaHRzPSJodHRwOi8vbnMuYWRvYmUuY29tL3hh\\ncC8xLjAvcmlnaHRzLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9t\\nbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3Vy\\nY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcFJpZ2h0\\nczpNYXJrZWQ9IkZhbHNlIiB4bXBNTTpPcmlnaW5hbERvY3VtZW50SUQ9InV1aWQ6MzEzRkMzNjgz\\nODZGRTIxMUI2ODM4MDZDNTlBNjMzOEQiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6Q0E2RTk2\\nRTBFNDdBMTFFMzhFMTQ4REY0NTBBMkU0OTgiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6Q0E2\\nRTk2REZFNDdBMTFFMzhFMTQ4REY0NTBBMkU0OTgiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhv\\ndG9zaG9wIENTNiAoV2luZG93cykiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJ\\nRD0ieG1wLmlpZDo0ODc2RDgzNDJBRUFFMzExQjVFQkQwQ0FBQTI2NzkwQSIgc3RSZWY6ZG9jdW1l\\n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\\nqrcjAAAAAElFTkSuQmCC\",\n" +
            "            \"instNum\": 0\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 2,\n" +
            "            \"label\": \"cache-service\",\n" +
            "            \"image\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAABQ0lEQVR42u2YDQ2DMBCFkYAEJCAB\\nCUhAAhKQgAQkIAEJSCDBwQx0ewtdCoHSjqQczfuSSzag5F7urzRJCCGEEELI45jn+TUMgzIN1x7j\\nfF3XKssy9fm7a7iHZ8SKgnNHzh/ZskYG4ziqPM+9RWjDWrzjdhFpmv4tQhvecauYK5HYi4zImqiq\\nSvV9r6Zp+hp+l2Upq2aWjnPoUNd16gjcs60N2s1s0SiKYuW4niEmeEZEVGxzom3bn8NIL33dFNM0\\njXXOiEgr1AEchZkdzVVIsPSCQ74dyYwSsEVUixYnxCx81+EpTgic1qAFuw7PpbPJEYIO5FoXwSNy\\nVuzbiOjC99kFBJslZ8V6xYK1X58tO4rbLHRxW3vX9NoiKq18ouIr5LYPrbMC9kmt27bxUX1YRfOp\\nG9XhQ3THQVEd0BFCCCGEkBVvlNA7bSC6TBYAAAAASUVORK5CYII=\",\n" +
            "            \"instNum\": 0\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 3,\n" +
            "            \"label\": \"[127.0.0.1:6379]\",\n" +
            "            \"image\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACcAAAAiCAYAAADcbsCGAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ\\nbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp\\nbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6\\neD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEz\\nNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo\\ndHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw\\ndGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAv\\nIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RS\\nZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpD\\ncmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBNYWNpbnRvc2giIHhtcE1NOkluc3RhbmNl\\nSUQ9InhtcC5paWQ6NDcwQzMwQzBFOTE5MTFERjhGMjdFMzgxNzhGRjI3QzMiIHhtcE1NOkRvY3Vt\\nZW50SUQ9InhtcC5kaWQ6NDcwQzMwQzFFOTE5MTFERjhGMjdFMzgxNzhGRjI3QzMiPiA8eG1wTU06\\nRGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpDRjNCN0FFMUU5MEUxMURGOEYy\\nN0UzODE3OEZGMjdDMyIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpDRjNCN0FFMkU5MEUxMURG\\nOEYyN0UzODE3OEZGMjdDMyIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1w\\nbWV0YT4gPD94cGFja2V0IGVuZD0iciI/Pgpu4WcAAAUhSURBVHjatFhPaBxVGP92d3b2T3bjLK2N\\nJBWC9tD2tCDVgIirYhG8BOvRYurFk2hvXqQUL940F/EgJlLxIAa3oAgFYaUXwYPBQ60e6oC2mNg0\\n2912s7Ozu+n3e5mXvrx98ydt8sHHDjM77/3e7/f7vpk3KdqDuHp82uGfWc7nOac5f+ZsHL3iNh5m\\n3NRDggKgNwNgpnA5v+RcZKDuvoNjQGDm3QAQjqn00klx7c5Pl8RvujxO+aPHqfPrL+qtdc6LDHJx\\nT8EpsoGlmn49O3WYnrh0mYbtFnWvXqHiiRlav/AFrX70oWm4JpjknI9jMxUDqqqw5OjXwdAjs69z\\nnqIcM6UGWFu/sLDNZkg0AtnrDLQZCy5gaS5gqRo1MhjLTh4WwDLj41R68WVx7F//lzxm0PvzD3F8\\nu/6t+P9gc5PuDgaUT6fJ5tTYrAdsLo+AY1C1ANCceheYKZx4RgBRA5Pfri+JXxkANr30A107+ZwA\\nJaM7HNKa71O736em3yP37h16vFCkI2NjNJHL62telkWUCqT7TppbDUyky6UHfAV/yYDfIClYajNL\\na72eACej3ffpL/amjJJl0bFSmY6xRbSop3vD4awJmJQtLrJTUyNeQwBOl8H5DDIsAOzJsZJgUQ0s\\n7EbXczLP2ulab7hZK2YylEnttGD7x++3jJ/LkXXw0REQa5/O063PPzNOjLEw+UHb3vYXEyEScz3l\\nODRTOUCPsazyOkD95/XoWqdDrX7ftXDyJlOPxEAH7CyVLXFa+CakHYwURfHpGa7esrABetyg1SL3\\n1KuivTg8HlKymNVIAGBmitbZlwOFaUv9kwSJlU3kbHKy2RE2ZVROv0WH3v8gFPT1d94WwHYsRBur\\nyWBWmCkUipH918oFFMQr6kmssOn36X8G2ufjQiY9ArL7+2/UurjEANqCObQSGSiQ5jdfGycEMxj3\\n786G+O0pxaI99s6IGS8fmVy41fPnwlaAKAv/ZFl222xufoRVTp+hDFcd5NQjTDo1cuy9yXwOc6Dv\\nnUUreY8PPhYm5+pa9bakDQsMAIAAqjVSY2DBkA4ShgXsAxtJryNYsYbldjYcoMVE8Np0scClnRcA\\nAdTTaPcEA12RegGpgf4GUFhwWDXjfoBSFwlWcd+q55EFEKC6wuglSNw4we0DiRXf7PnGlasFhNXL\\nwfFfz+ylben0YoPsmGeFQUnZLTmgnAgsyEqVlCNNN2/3PGYnjCHVswClsxxlJcvkEaT0FoBihXaw\\nYuSaWIhPUQUkA9JJRXTZY8ZYTn01WakGBVGLmsDkrbBV37eFvUM66ScAi5Kd73UP5ewXUsGr9gKv\\nwImrqrDmfN9nm/yftFiEej1JFzC0qobFq6jyCQcXkfDWSjCQyVtonplUd0elAYip/0Ey9LYo+U2q\\nYJ5/NrpkYTJoLxnBZGglyLB2sCWPJ9LUo+L8JGXXe6XeEy29CFhrsRrJBjJKFgwUZQWTLXSWw0iw\\n9AYLOiEF+h6AYsAkzTmuYnXpEhRH0wpei5vqBkbve9KoSZtz1BMgSXEEu7OzoqS4nci96JxplxU1\\nmXygwxZe8CIpfahWbMLeCFDn37ix7o7svhik3J+eC3t1D3tQh70exfU15avAJwyqmWjfykCNu7Ek\\nz0kJyvSo0/esDGjxgXf8geRyHzsdJrksHHgqgXQAFfuRZ1ffShjobODN2i4/sUCueQCTftq3r0xJ\\nCkjx0/ko6fbtE1hEAaE9zT8oqD0BpwGtBiy6u5EuKu4JMAB2XC82Kte/RwAAAABJRU5ErkJggg==\",\n" +
            "            \"instNum\": 0\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 4,\n" +
            "            \"label\": \"[localhost:-1]\",\n" +
            "            \"image\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAABQ0lEQVR42u2YDQ2DMBCFkYAEJCAB\\nCUhAAhKQgAQkIAEJSCDBwQx0ewtdCoHSjqQczfuSSzag5F7urzRJCCGEEELI45jn+TUMgzIN1x7j\\nfF3XKssy9fm7a7iHZ8SKgnNHzh/ZskYG4ziqPM+9RWjDWrzjdhFpmv4tQhvecauYK5HYi4zImqiq\\nSvV9r6Zp+hp+l2Upq2aWjnPoUNd16gjcs60N2s1s0SiKYuW4niEmeEZEVGxzom3bn8NIL33dFNM0\\njXXOiEgr1AEchZkdzVVIsPSCQ74dyYwSsEVUixYnxCx81+EpTgic1qAFuw7PpbPJEYIO5FoXwSNy\\nVuzbiOjC99kFBJslZ8V6xYK1X58tO4rbLHRxW3vX9NoiKq18ouIr5LYPrbMC9kmt27bxUX1YRfOp\\nG9XhQ3THQVEd0BFCCCGEkBVvlNA7bSC6TBYAAAAASUVORK5CYII=\",\n" +
            "            \"instNum\": 0\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 5,\n" +
            "            \"label\": \"persistence-service\",\n" +
            "            \"image\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ\\nbWFnZVJlYWR5ccllPAAAA69pVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp\\nbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6\\neD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0\\nNTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo\\ndHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw\\ndGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wUmlnaHRzPSJodHRwOi8vbnMuYWRvYmUuY29tL3hh\\ncC8xLjAvcmlnaHRzLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9t\\nbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3Vy\\nY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcFJpZ2h0\\nczpNYXJrZWQ9IkZhbHNlIiB4bXBNTTpPcmlnaW5hbERvY3VtZW50SUQ9InV1aWQ6MzEzRkMzNjgz\\nODZGRTIxMUI2ODM4MDZDNTlBNjMzOEQiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6Q0E2RTk2\\nRTBFNDdBMTFFMzhFMTQ4REY0NTBBMkU0OTgiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6Q0E2\\nRTk2REZFNDdBMTFFMzhFMTQ4REY0NTBBMkU0OTgiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhv\\ndG9zaG9wIENTNiAoV2luZG93cykiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJ\\nRD0ieG1wLmlpZDo0ODc2RDgzNDJBRUFFMzExQjVFQkQwQ0FBQTI2NzkwQSIgc3RSZWY6ZG9jdW1l\\n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\\nqrcjAAAAAElFTkSuQmCC\",\n" +
            "            \"instNum\": 0\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 6,\n" +
            "            \"label\": \"[127.0.0.1:3307]\",\n" +
            "            \"image\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ\\nbWFnZVJlYWR5ccllPAAAA69pVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp\\nbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6\\neD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0\\nNTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo\\ndHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw\\ndGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wUmlnaHRzPSJodHRwOi8vbnMuYWRvYmUuY29tL3hh\\ncC8xLjAvcmlnaHRzLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9t\\nbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3Vy\\nY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcFJpZ2h0\\nczpNYXJrZWQ9IkZhbHNlIiB4bXBNTTpPcmlnaW5hbERvY3VtZW50SUQ9InV1aWQ6MzEzRkMzNjgz\\nODZGRTIxMUI2ODM4MDZDNTlBNjMzOEQiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MkEzMERF\\nOUZFNDdBMTFFMzhFMTQ4REY0NTBBMkU0OTgiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MkEz\\nMERFOUVFNDdBMTFFMzhFMTQ4REY0NTBBMkU0OTgiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhv\\ndG9zaG9wIENTNiAoV2luZG93cykiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJ\\nRD0ieG1wLmlpZDo0ODc2RDgzNDJBRUFFMzExQjVFQkQwQ0FBQTI2NzkwQSIgc3RSZWY6ZG9jdW1l\\n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\\nABaXiPU/ABCeAAAAAElFTkSuQmCC\",\n" +
            "            \"instNum\": 0\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 7,\n" +
            "            \"label\": \"[127.0.0.1:8002]\",\n" +
            "            \"image\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAABQ0lEQVR42u2YDQ2DMBCFkYAEJCAB\\nCUhAAhKQgAQkIAEJSCDBwQx0ewtdCoHSjqQczfuSSzag5F7urzRJCCGEEELI45jn+TUMgzIN1x7j\\nfF3XKssy9fm7a7iHZ8SKgnNHzh/ZskYG4ziqPM+9RWjDWrzjdhFpmv4tQhvecauYK5HYi4zImqiq\\nSvV9r6Zp+hp+l2Upq2aWjnPoUNd16gjcs60N2s1s0SiKYuW4niEmeEZEVGxzom3bn8NIL33dFNM0\\njXXOiEgr1AEchZkdzVVIsPSCQ74dyYwSsEVUixYnxCx81+EpTgic1qAFuw7PpbPJEYIO5FoXwSNy\\nVuzbiOjC99kFBJslZ8V6xYK1X58tO4rbLHRxW3vX9NoiKq18ouIr5LYPrbMC9kmt27bxUX1YRfOp\\nG9XhQ3THQVEd0BFCCCGEkBVvlNA7bSC6TBYAAAAASUVORK5CYII=\",\n" +
            "            \"instNum\": 0\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 8,\n" +
            "            \"label\": \"[192.168.1.11:20880]\",\n" +
            "            \"image\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAABQ0lEQVR42u2YDQ2DMBCFkYAEJCAB\\nCUhAAhKQgAQkIAEJSCDBwQx0ewtdCoHSjqQczfuSSzag5F7urzRJCCGEEELI45jn+TUMgzIN1x7j\\nfF3XKssy9fm7a7iHZ8SKgnNHzh/ZskYG4ziqPM+9RWjDWrzjdhFpmv4tQhvecauYK5HYi4zImqiq\\nSvV9r6Zp+hp+l2Upq2aWjnPoUNd16gjcs60N2s1s0SiKYuW4niEmeEZEVGxzom3bn8NIL33dFNM0\\njXXOiEgr1AEchZkdzVVIsPSCQ74dyYwSsEVUixYnxCx81+EpTgic1qAFuw7PpbPJEYIO5FoXwSNy\\nVuzbiOjC99kFBJslZ8V6xYK1X58tO4rbLHRxW3vX9NoiKq18ouIr5LYPrbMC9kmt27bxUX1YRfOp\\nG9XhQ3THQVEd0BFCCCGEkBVvlNA7bSC6TBYAAAAASUVORK5CYII=\",\n" +
            "            \"instNum\": 0\n" +
            "        }\n" +
            "    ],\n" +
            "    \"nodeRefs\": [\n" +
            "        {\n" +
            "            \"from\": 0,\n" +
            "            \"to\": 1,\n" +
            "            \"resSum\": 2\n" +
            "        },\n" +
            "        {\n" +
            "            \"from\": 2,\n" +
            "            \"to\": 3,\n" +
            "            \"resSum\": 5\n" +
            "        },\n" +
            "        {\n" +
            "            \"from\": 2,\n" +
            "            \"to\": 4,\n" +
            "            \"resSum\": 4\n" +
            "        },\n" +
            "        {\n" +
            "            \"from\": 5,\n" +
            "            \"to\": 6,\n" +
            "            \"resSum\": 4\n" +
            "        },\n" +
            "        {\n" +
            "            \"from\": 1,\n" +
            "            \"to\": 7,\n" +
            "            \"resSum\": 4\n" +
            "        },\n" +
            "        {\n" +
            "            \"from\": 1,\n" +
            "            \"to\": 8,\n" +
            "            \"resSum\": 2\n" +
            "        }\n" +
            "    ]\n" +
            "}";
}